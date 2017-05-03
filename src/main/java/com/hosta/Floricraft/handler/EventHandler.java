package com.hosta.Floricraft.handler;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Ordering;
import com.hosta.Floricraft.config.ConfigChecker;
import com.hosta.Floricraft.helper.DateHelper;
import com.hosta.Floricraft.helper.EntityHelper;
import com.hosta.Floricraft.helper.MineHelper;
import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.packet.PacketNBTGui;
import com.hosta.Floricraft.world.biome.BiomeBasicWithPath;
import com.hosta.Floricraft.world.biome.BiomeFlowerLand;
import com.hosta.Floricraft.world.gen.feature.WorldGenSimpleRoad;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventHandler {

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		String text = null;
		
		if (DateHelper.isNewYear())			{text = "new_year";	}
		else if (DateHelper.isChristmas())	{text = "christmas";}
		/*else								{text = "welcome";}*/
		
		if (text != null)
		{
			EntityPlayer player = event.player;
			player.sendMessage(new TextComponentTranslation("message.event." + text));
			
			NBTTagCompound nbt =new NBTTagCompound();
			nbt.setString("Text", text);
			nbt.setInteger("Tick", 400);
			FloricraftInit.NETWORK_GUI.sendTo(new PacketNBTGui(nbt), (EntityPlayerMP) player);
			
			MineHelper.spawnFireworksAround(player.getEntityWorld(), player.getPosition(), 3, 8);
		}
	}
	
	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemCraftedEvent event)
	{
		if(event.crafting.getItem() == FloricraftInit.PURNER)
		{
			for(int i = 0; i < 9; i++)
			{
				ItemStack itemStack = event.craftMatrix.getStackInSlot(i);
				if(itemStack != null && itemStack.getItem() == FloricraftInit.PURNER)
				{
					event.craftMatrix.removeStackFromSlot(i);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if(!event.getEntity().world.isRemote)
		{
			final EntityLivingBase entity = event.getEntityLiving();
			if(entity instanceof EntityZombie)
			{
				final EntityZombie zombi = (EntityZombie)entity;
			
				final BlockPos pos = zombi.getPosition();
				final BlockPos posDown = pos.down();
			
				if(zombi.world.getBlockState(posDown) == Blocks.DIRT.getDefaultState())
				{
					if(zombi.world.getBlockState(pos) == Blocks.AIR.getDefaultState())
					{
						zombi.world.setBlockState(pos, FloricraftInit.LYCORIS.getDefaultState());
					}
				}
			}
		}
	}

	/*
	@SubscribeEvent
	public void onEntityAttack(LivingAttackEvent event)
	{
		Entity attacker = event.getSource().getEntity();
		if (attacker != null && attacker instanceof EntityLivingBase)
		{
			ItemStack itemStack = ((EntityLivingBase)attacker).getHeldItemMainhand();
			if (itemStack != null)
			{
				if (MineHelper.hasEnchantment(itemStack, FloricraftInit.enchantment_floric))
				{
					
				}
			}
		}
	}
	*/
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		final EntityLivingBase entity = event.getEntityLiving();
		
		if(entity.isPotionActive(FloricraftInit.POTION_FLORIC))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_FLORIC).getAmplifier();
			int healTick = 200 / ++amplifier;
			if(entity.getHealth() < entity.getMaxHealth() && entity.ticksExisted % (healTick < 4 ? 4 : healTick) == 0)
			{
				entity.heal(1.0f);
			}

			Collection<PotionEffect> collection = entity.getActivePotionEffects();
			List<PotionEffect> list = Ordering.natural().sortedCopy(collection);
			list.forEach(potionEffect -> PotionHelper.decreaseBadPotionEffect(entity, potionEffect));
			
			if (ConfigChecker.isHillStepEnabled())
			{
				entity.stepHeight = 1;
			}
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_TEMPTATION))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_TEMPTATION).getAmplifier();
			EntityAnimal nearest = null;
			AxisAlignedBB aabb = entity.getEntityBoundingBox().expand(8 + (amplifier * 4), 4 + (amplifier * 2), 8 + (amplifier * 4));
			nearest = (EntityAnimal)entity.world.findNearestEntityWithinAABB(EntityAnimal.class, aabb, entity);
			
			if(nearest != null)
			{
				nearest.addPotionEffect(new PotionEffect(FloricraftInit.POTION_TEMPTED, 200, amplifier, true, false));
			}
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_TEMPTED))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_TEMPTED).getAmplifier();
			EntityPlayer temptation = null;
			AxisAlignedBB aabb = entity.getEntityBoundingBox().expand(8 + (amplifier * 4), 4 + (amplifier * 2), 8 + (amplifier * 4));
			temptation = (EntityPlayer)entity.world.findNearestEntityWithinAABB(EntityPlayer.class, aabb, entity);
			
			if(temptation != null && temptation.isPotionActive(FloricraftInit.POTION_TEMPTATION) && entity instanceof EntityLiving)
			{
				((EntityLiving) entity).getNavigator().tryMoveToEntityLiving(temptation, 1.5 + (amplifier * 0.1));
				EntityHelper.spawnEntityParticle(entity,EnumParticleTypes.HEART);
			}
			
			else if(entity instanceof EntityAnimal)
			{
				final EntityAnimal animal = (EntityAnimal)entity;
				if(animal.getGrowingAge() == 0 && !animal.isInLove())
				{
					animal.setInLove(null);
				}
			}
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_ANTI_ZOMBIE))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_ANTI_ZOMBIE).getAmplifier();
			EntityHelper.antiEntityFrom(entity, EntityZombie.class, amplifier);
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_ANTI_SKELETON))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_ANTI_SKELETON).getAmplifier();
			EntityHelper.antiEntityFrom(entity, EntitySkeleton.class, amplifier);
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_ANTI_CREEPER))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_ANTI_CREEPER).getAmplifier();
			EntityHelper.antiEntityFrom(entity, EntityCreeper.class, amplifier);
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_ANTI_SPIDER))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_ANTI_SPIDER).getAmplifier();
			EntityHelper.antiEntityFrom(entity, EntitySpider.class, amplifier);
		}
		
		if(entity.isPotionActive(FloricraftInit.POTION_ANTI_ENDERMAN))
		{
			int amplifier = entity.getActivePotionEffect(FloricraftInit.POTION_ANTI_ENDERMAN).getAmplifier();
			EntityHelper.antiEntityFrom(entity, EntityEnderman.class, amplifier);
		}
	}

	@SubscribeEvent
	public void onChunkPopulate(PopulateChunkEvent.Post event)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				BlockPos topPos = new BlockPos(event.getChunkX() * 16 + x, 0, event.getChunkZ() * 16 + z);
				World world = event.getWorld();
				Biome biome = world.getBiome(topPos);
				
				if (biome instanceof BiomeBasicWithPath)
				{
					WorldGenSimpleRoad[] simpleRoads = {new WorldGenSimpleRoad(2.0f, 5.0f, 5.0f, 300), new WorldGenSimpleRoad(-0.5f, 50.0f, 50.0f, 200)};
					topPos = world.getTopSolidOrLiquidBlock(topPos);
					
					boolean pathFlag = false;
					for (WorldGenSimpleRoad simpleRoad: simpleRoads)
					{
						simpleRoad.setSegment(topPos.getX());
						if (simpleRoad.isOnRoad(topPos.getZ()))
						{
							pathFlag = true;
						}
					}
					
					if(pathFlag)
					{
						boolean genFlag = false;
						topPos = topPos.down();

						if (!genFlag)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genPath(world, topPos);
							}
						}
						if (!genFlag)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genGrass(world, topPos);
							}
						}
						if (!genFlag)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genWaterway(world, topPos);
							}
						}
						if (!genFlag && z % 16 != 0)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genFence(world, topPos);
							}
						}
					}
					else if (biome instanceof BiomeFlowerLand && x % 4 != 0 && z % 16 != 0)
					{
						BiomeFlowerLand biomeFarmer = (BiomeFlowerLand)biome;
						if (world.getBlockState(topPos.down()).getBlock() == biomeFarmer.farmLand.getBlock() && world.isAirBlock(topPos))
						{
							int i = (topPos.getX() / 4) % biomeFarmer.crops.length;
							i = i < 0 ? i + biomeFarmer.crops.length : i;
							
							world.setBlockState(topPos, biomeFarmer.crops[i]);
							if (biomeFarmer.crops[i].getBlock() instanceof BlockDoublePlant)
							{
								world.setBlockState(topPos.up(), biomeFarmer.crops[i].withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER));
							}
						}
					}
				}
			}
		}
	}
}
