package com.hosta.Floricraft.mod.baubles;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ToolSachet;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaublesItemMetaAmuletSachet extends ToolSachet implements IBauble{

	public BaublesItemMetaAmuletSachet(String unlocalizedName)
	{
		super(unlocalizedName);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack)
	{
		return BaubleType.AMULET;
	}
	
	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		if(player instanceof EntityPlayer) 
		{
			EntityPlayer entityPlayer = (EntityPlayer) player ;
			{
				checkAndAddEffectPlayer(itemstack, this, entityPlayer, FloricraftInit.POTION_FLORIC, 0);
				checkAndAddEffectPlayer(itemstack, BaublesFloricraftInit.AMULET_SACHET_TEMPTATION, entityPlayer, FloricraftInit.POTION_TEMPTATION, 0);
				checkAndAddEffectPlayer(itemstack, BaublesFloricraftInit.AMULET_SACHET_ANTI_ZOMBIE, entityPlayer, FloricraftInit.POTION_ANTI_ZOMBIE, 0);
				checkAndAddEffectPlayer(itemstack, BaublesFloricraftInit.AMULET_SACHET_ANTI_SKELETON, entityPlayer, FloricraftInit.POTION_ANTI_SKELETON, 0);
				checkAndAddEffectPlayer(itemstack, BaublesFloricraftInit.AMULET_SACHET_ANTI_CREEPER, entityPlayer, FloricraftInit.POTION_ANTI_CREEPER, 0);
				checkAndAddEffectPlayer(itemstack, BaublesFloricraftInit.AMULET_SACHET_ANTI_SPIDER, entityPlayer, FloricraftInit.POTION_ANTI_SPIDER, 0);
				checkAndAddEffectPlayer(itemstack, BaublesFloricraftInit.AMULET_SACHET_ANTI_ENDERMAN, entityPlayer, FloricraftInit.POTION_ANTI_ENDERMAN, 0);
			}
			
			damageItem(itemstack, entityPlayer, getBaubleType(itemstack).getValidSlots()[0]);
			
			IBauble.super.onWornTick(itemstack, player);
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		
	}
	
	@SuppressWarnings("deprecation")
	private void damageItem(ItemStack stack, EntityPlayer player, int itemSlot)
	{
		if (stack.getItemDamage() < this.getMaxDamage())
		{
			if(player.ticksExisted % 20 == 0)
			{
				stack.damageItem(1, player);
			}
		}
		else if (stack.getItemDamage() == this.getMaxDamage())
		{
			EntityItem entityItem = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FloricraftInit.SACHET));
			
			player.renderBrokenItemStack(stack);
			BaublesApi.getBaubles(player).removeStackFromSlot(itemSlot);
			
			if (!player.worldObj.isRemote)
			{
				player.worldObj.spawnEntityInWorld(entityItem);
			}
		}
	}
}
