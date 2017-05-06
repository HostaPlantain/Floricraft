package com.hosta.Floricraft.init;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.block.BlockCropHemp;
import com.hosta.Floricraft.block.BlockDollIronSit;
import com.hosta.Floricraft.block.BlockFlowerLycoris;
import com.hosta.Floricraft.block.BlockFlowerPot;
import com.hosta.Floricraft.block.BlockLeavesChristmasDynamic;
import com.hosta.Floricraft.block.BlockLeavesChristmasNormal;
import com.hosta.Floricraft.block.BlockLeavesFloric;
import com.hosta.Floricraft.block.BlockOreSalt;
import com.hosta.Floricraft.block.BlockRoundBaleHay;
import com.hosta.Floricraft.block.BlockRoundBaleSilage;
import com.hosta.Floricraft.block.BlockSalt;
import com.hosta.Floricraft.block.BlockSaplingChristmas;
import com.hosta.Floricraft.block.BlockSapringFloric;
import com.hosta.Floricraft.block.BlockStackDead;
import com.hosta.Floricraft.block.BlockStackMeta;
import com.hosta.Floricraft.block.BlockTorchFloric;
import com.hosta.Floricraft.block.BlockWeatherCock;
import com.hosta.Floricraft.block.BlockWreath;
import com.hosta.Floricraft.config.ConfigChecker;
import com.hosta.Floricraft.enchantment.EnchantmentFloric;
import com.hosta.Floricraft.handler.EnumHandler.EnumDamaged;
import com.hosta.Floricraft.handler.EnumHandler.EnumDrying;
import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;
import com.hosta.Floricraft.handler.EnumHandler.EnumWeatherCock;
import com.hosta.Floricraft.item.ItemArmorCloth;
import com.hosta.Floricraft.item.ItemBasic;
import com.hosta.Floricraft.item.ItemBlockMeta;
import com.hosta.Floricraft.item.ItemBlockMetaWood;
import com.hosta.Floricraft.item.ItemBottleBrown;
import com.hosta.Floricraft.item.ItemFoodConfection;
import com.hosta.Floricraft.item.ItemFoodSugared;
import com.hosta.Floricraft.item.ItemHolderBasket;
import com.hosta.Floricraft.item.ItemHolderSachet;
import com.hosta.Floricraft.item.ItemMetaFlower;
import com.hosta.Floricraft.item.ItemMetaSachet;
import com.hosta.Floricraft.item.ItemSeedsHemp;
import com.hosta.Floricraft.item.ItemToolPurner;
import com.hosta.Floricraft.packet.PacketNBTGui;
import com.hosta.Floricraft.packet.PacketNBTGuiHandler;
import com.hosta.Floricraft.potion.EffectBasic;
import com.hosta.Floricraft.tileentity.TileEntityDollIronSit;
import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;
import com.hosta.Floricraft.tileentity.TileEntityWeatherCock;
import com.hosta.Floricraft.world.biome.BiomeFlowerLand;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

public class FloricraftInit{
	
	//Flower
	public static final Item FLOWER_CUT = new ItemMetaFlower("flower_cut").setMaxStackSize(16);
	//public static final Item flower_bouquet = new ItemMetaFlower("flower_bouquet").setMaxStackSize(1);
	public static final Item PETAL_RAW = new ItemMetaFlower("petal_raw");
	public static final Item PETAL_DRY = new ItemMetaFlower("petal_dry");
	public static final Item PETAL_SALTY = new ItemMetaFlower("petal_salty");
	public static final Item PETAL_SUGARED = new ItemFoodSugared("petal_sugared", 1, 0.5F);
	public static final Item PETALS_RAW = new ItemMetaFlower("petals_raw");
	public static final Item PETALS_DRY = new ItemMetaFlower("petals_dry");
	public static final Item PETALS_SALTY = new ItemMetaFlower("petals_salty");
	public static final Item PETALS_SUGARED = new ItemFoodSugared("petals_sugared", 2, 1.0F);
			
	public static final Block STACK_DANDELION = new BlockStackMeta("stack_dandelion", Material.LEAVES);
	public static final Block STACK_POPPY = new BlockStackMeta("stack_poppy", Material.LEAVES);
	public static final Block STACK_BLUE_ORCHID = new BlockStackMeta("stack_blue_orchid", Material.LEAVES);
	public static final Block STACK_ALLIUM = new BlockStackMeta("stack_allium", Material.LEAVES);
	public static final Block STACK_AZURE_BLUET = new BlockStackMeta("stack_azure_bluet", Material.LEAVES);
	public static final Block STACK_RED_TULIP = new BlockStackMeta("stack_red_tulip", Material.LEAVES);
	public static final Block STACK_ORANGE_TULIP = new BlockStackMeta("stack_orange_tulip", Material.LEAVES);
	public static final Block STACK_WHITE_TULIP = new BlockStackMeta("stack_white_tulip", Material.LEAVES);
	public static final Block STACK_PINK_TULIP = new BlockStackMeta("stack_pink_tulip", Material.LEAVES);
	public static final Block STACK_OXEYE_DAISY = new BlockStackMeta("stack_oxeye_daisy", Material.LEAVES);
	public static final Block STACK_SUNFLOWER = new BlockStackMeta("stack_sunflower", Material.LEAVES);
	public static final Block STACK_LILAC = new BlockStackMeta("stack_lilac", Material.LEAVES);
	public static final Block STACK_ROSE = new BlockStackMeta("stack_rose", Material.LEAVES);
	public static final Block STACK_PEONY = new BlockStackMeta("stack_peony", Material.LEAVES);
	public static final Block STACK_SAKURA = new BlockStackMeta("stack_sakura", Material.LEAVES);
	
	public static final Block STACK_DEAD = new BlockStackDead("stack_dead", Material.LEAVES);
	
	//Sachet
	public static final Item SACHET = new ItemBasic("sachet");
	public static final Item SACHET_FLOWER = new ItemMetaSachet("sachet_flower"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_TEMPTATION = new ItemMetaSachet("sachet_temptation"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_ANTI_ZOMBIE = new ItemMetaSachet("sachet_anti_zombie"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_ANTI_SKELETON = new ItemMetaSachet("sachet_anti_skeleton"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_ANTI_CREEPER = new ItemMetaSachet("sachet_anti_creeper"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_ANTI_SPIDER = new ItemMetaSachet("sachet_anti_spider"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_ANTI_ENDERMAN = new ItemMetaSachet("sachet_anti_enderman"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item SACHET_HOLDER = new ItemHolderSachet("sachet_holder");
	
	//Basket
	public static final Item BASKET_FLOWER = new ItemHolderBasket("basket_flower", 2);
	public static final Item BASKET_LUNCH = new ItemHolderBasket("basket_lunch", 3);
	
	//Crop
	//Hemp
	public static final Block CROP_HEMP = new BlockCropHemp("crop_hemp");
	public static final Item SEED_HEMP = new ItemSeedsHemp("seed_hemp", CROP_HEMP, Blocks.FARMLAND);
	public static final Item HEMP_YARN = new ItemBasic("hemp_yarn");
	public static final Item HEMP_TWINE = new ItemBasic("hemp_twine");
	public static final Item HEMP_CLOTH = new ItemBasic("hemp_cloth");
	
	//ClothArmor
	public static final ArmorMaterial CLOTH = EnumHelper.addArmorMaterial("cloth", Reference.MOD_ID + ":" + "cloth", 5, new int[]{1, 3, 2, 1}, 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	public static final Item CLOTH_HELMET = new ItemArmorCloth("cloth_helmet", CLOTH, 1, EntityEquipmentSlot.HEAD);
	public static final Item CLOTH_CHESTPLATE = new ItemArmorCloth("cloth_chestplate", CLOTH, 1, EntityEquipmentSlot.CHEST);
	public static final Item CLOTH_LEGGINGS = new ItemArmorCloth("cloth_leggings", CLOTH, 2, EntityEquipmentSlot.LEGS);
	public static final Item CLOTH_BOOTS = new ItemArmorCloth("cloth_boots", CLOTH, 1, EntityEquipmentSlot.FEET);
	public static final ArmorMaterial APRON = EnumHelper.addArmorMaterial("apron", Reference.MOD_ID + ":" + "apron", 3, new int[]{1, 3, 2, 1}, 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	public static final Item APRON_CHESTPLATE = new ItemArmorCloth("apron_chestplate", APRON, 1, EntityEquipmentSlot.CHEST);
	
	//PotPourri
	//public static final Block potpourri = new BlockPotPourri("potpourri", Material.GLASS).setCreativeTab(Floricraft.tabFloricraft);
	
	//Doll
	public static final Block DOLL_IRON_SIT = new BlockDollIronSit("doll_iron_sit");
	
	//Wether
	public static final Block WEATHER_COCK = new BlockWeatherCock("weather_cock");
	
	//Vase
	//public static final Item vase_raw = new Item().setUnlocalizedName("vase_raw").setCreativeTab(Floricraft.tabFloricraft);
	//public static final Item vase_rare = new ItemMetaColor("vase_rare");
	
	//Plater
	public static final Block FLOWER_POT = new BlockFlowerPot("flower_pot");
	
	//Torch
	public static final Block FLORIC_TORCH_DANDELION = new BlockTorchFloric("floric_torch_dandelion");
	public static final Block FLORIC_TORCH_POPPY = new BlockTorchFloric("floric_torch_poppy");
	public static final Block FLORIC_TORCH_BLUE_ORCHID = new BlockTorchFloric("floric_torch_blue_orchid");
	public static final Block FLORIC_TORCH_ALLIUM = new BlockTorchFloric("floric_torch_allium");
	public static final Block FLORIC_TORCH_AZURE_BLUET = new BlockTorchFloric("floric_torch_azure_bluet");
	public static final Block FLORIC_TORCH_RED_TULIP = new BlockTorchFloric("floric_torch_red_tulip");
	public static final Block FLORIC_TORCH_ORANGE_TULIP = new BlockTorchFloric("floric_torch_orange_tulip");
	public static final Block FLORIC_TORCH_WHITE_TULIP = new BlockTorchFloric("floric_torch_white_tulip");
	public static final Block FLORIC_TORCH_PINK_TULIP = new BlockTorchFloric("floric_torch_pink_tulip");
	public static final Block FLORIC_TORCH_OXEYE_DAISY = new BlockTorchFloric("floric_torch_oxeye_daisy");
	public static final Block FLORIC_TORCH_SUNFLOWER = new BlockTorchFloric("floric_torch_sunflower");
	public static final Block FLORIC_TORCH_LILAC = new BlockTorchFloric("floric_torch_lilac");
	public static final Block FLORIC_TORCH_ROSE = new BlockTorchFloric("floric_torch_rose");
	public static final Block FLORIC_TORCH_PEONY = new BlockTorchFloric("floric_torch_peony");
	public static final Block FLORIC_TORCH_SAKURA = new BlockTorchFloric("floric_torch_sakura");
	
	//Silage
	public static final Block ROUND_BALE_HAY = new BlockRoundBaleHay("round_bale_hay");
	public static final Block ROUND_BALE_SILAGE = new BlockRoundBaleSilage("round_bale_silage");
	
	//flower
	public static final Block LYCORIS = new BlockFlowerLycoris("lycoris");
	
	//Sakura
	public static final Block LEAVES_FLORIC_TYPE0 = new BlockLeavesFloric("leaves_floric_type0");
	public static final Block SAPLING_FLORIC_TYPE0 = new BlockSapringFloric("sapling_floric_type0");
	
	//Salt
	public static final Block ORE_SALT = new BlockOreSalt("ore_salt", Material.ROCK);
	public static final Item DUST_SALT = new ItemBasic("dust_salt");
	public static final Block BLOCK_SALT = new BlockSalt("block_salt", Material.SAND);
	
	//Tool
	public static final Item PURNER = new ItemToolPurner("purner");
	
	//PotionItem
	public static final Item BOTTLE_BROWN_EMPTY = new ItemBottleBrown("bottle_brown_empty").setMaxStackSize(1);
	public static final Item BOTTLE_BROWN_WATER = new ItemBasic("bottle_brown_water").setMaxStackSize(1);
	public static final Item BOTTLE_BROWN_FLOWER = new ItemMetaFlower("bottle_brown_flower").setMaxStackSize(1);
	
	//Event
	public static final Item CONFECTION_EVENT = new ItemFoodConfection("confection_event", 1, 0.5F);
	
	//Christmas
	public static final Block LEAVES_CHRISTMAS = new BlockLeavesChristmasNormal("leaves_christmas", 1.0F);
	public static final Block LEAVES_CHRISTMAS_DYNAMIC = new BlockLeavesChristmasDynamic("leaves_christmas_dynamic", 1.0F);
	public static final Block LEAVES_CHRISTMAS_DYNAMIC_UNLIT = new BlockLeavesChristmasDynamic("leaves_christmas_dynamic_unlit", 0.0F);
	public static final Block LEAVES_CHRISTMAS_UNLIT = new BlockLeavesChristmasDynamic("leaves_christmas_unlit", 0.0F);
	public static final Block ORNAMENT_CHRISTMAS = new BlockWreath("ornament_christmas");
	public static final Block SAPLING_CHRISTMAS = new BlockSaplingChristmas("sapling_christmas");
	
	//Potion
	public static final Potion POTION_FLORIC = new EffectBasic(false, 16767743).setIconIndex(0, 0).setPotionName("effect.floric").setBeneficial();
	public static final Potion POTION_TEMPTATION = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.temptation").setBeneficial();
	public static final Potion POTION_TEMPTED = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.tempted").setBeneficial();
	public static final Potion POTION_ANTI_ZOMBIE = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.anti_zombie").setBeneficial();
	public static final Potion POTION_ANTI_SKELETON = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.anti_skeleton").setBeneficial();
	public static final Potion POTION_ANTI_CREEPER = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.anti_creeper").setBeneficial();
	public static final Potion POTION_ANTI_SPIDER = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.anti_spider").setBeneficial();
	public static final Potion POTION_ANTI_ENDERMAN = new EffectBasic(false, 0).setIconIndex(0, 0).setPotionName("effect.anti_enderman").setBeneficial();
	
	//Enchantment
	public static final Enchantment ENCHANTMENT_FLORIC = new EnchantmentFloric("floric", Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});

	//Biome
	public static final Biome BIOME_ROSE_LAND = new BiomeFlowerLand("Rose Land").setCrops(Blocks.DOUBLE_PLANT.getStateFromMeta(4));
	public static final Biome BIOME_TULIP_LAND = new BiomeFlowerLand("Tulip Land").setCropsbyTypes(1);
	
	//Network
	public static final SimpleNetworkWrapper NETWORK_GUI = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID + "." + "gui");
	
	
	public static void init()
	{
		//ClothArmor
		CLOTH.repairMaterial = new ItemStack(HEMP_CLOTH);
		APRON.repairMaterial = new ItemStack(HEMP_CLOTH);
	}
	
	public static void registers()
	{
		//Flower
		Registerer.register(FLOWER_CUT);
		//Register.register(flower_bouquet);
		Registerer.register(PETAL_RAW);
		Registerer.register(PETAL_DRY);
		Registerer.register(PETAL_SALTY);
		Registerer.register(PETAL_SUGARED);
		Registerer.register(PETALS_RAW);
		Registerer.register(PETALS_DRY);
		Registerer.register(PETALS_SALTY);
		Registerer.register(PETALS_SUGARED);
		
		Registerer.registerWithMeta(STACK_DANDELION);
		Registerer.registerWithMeta(STACK_POPPY);
		Registerer.registerWithMeta(STACK_BLUE_ORCHID);
		Registerer.registerWithMeta(STACK_ALLIUM);
		Registerer.registerWithMeta(STACK_AZURE_BLUET);
		Registerer.registerWithMeta(STACK_RED_TULIP);
		Registerer.registerWithMeta(STACK_ORANGE_TULIP);
		Registerer.registerWithMeta(STACK_WHITE_TULIP);
		Registerer.registerWithMeta(STACK_PINK_TULIP);
		Registerer.registerWithMeta(STACK_OXEYE_DAISY);
		Registerer.registerWithMeta(STACK_SUNFLOWER);
		Registerer.registerWithMeta(STACK_LILAC);
		Registerer.registerWithMeta(STACK_ROSE);
		Registerer.registerWithMeta(STACK_PEONY);
		Registerer.registerWithMeta(STACK_SAKURA);

		Registerer.register(STACK_DEAD);
		
		//Sachet
		Registerer.register(SACHET);
		Registerer.register(SACHET_FLOWER);
		Registerer.register(SACHET_TEMPTATION);
		Registerer.register(SACHET_ANTI_ZOMBIE);
		Registerer.register(SACHET_ANTI_SKELETON);
		Registerer.register(SACHET_ANTI_CREEPER);
		Registerer.register(SACHET_ANTI_SPIDER);
		Registerer.register(SACHET_ANTI_ENDERMAN);
		Registerer.register(SACHET_HOLDER);
		
		//Basket
		Registerer.register(BASKET_FLOWER);
		Registerer.register(BASKET_LUNCH);
		
		//Crop
		//Hemp
		Registerer.register(SEED_HEMP);
		Registerer.registerBlockWithOutItem(CROP_HEMP);
		Registerer.register(HEMP_YARN);
		Registerer.register(HEMP_TWINE);
		Registerer.register(HEMP_CLOTH);
		
		//ClothArmor
		Registerer.register(CLOTH_HELMET);
		Registerer.register(CLOTH_CHESTPLATE);
		Registerer.register(CLOTH_LEGGINGS);
		Registerer.register(CLOTH_BOOTS);
		Registerer.register(APRON_CHESTPLATE);
		
		//PotPourri
		//Register.register(potpourri);
		//GameRegistry.registerTileEntity(TileEntityPotPourri.class, potpourri.getUnlocalizedName());
		
		//Doll
		Registerer.registerWithTileEntity(DOLL_IRON_SIT, TileEntityDollIronSit.class);
		
		//Wether
		Registerer.registerWithTileEntityWithMeta(WEATHER_COCK, TileEntityWeatherCock.class);
		
		//Vase
		//Register.register(vase_raw);
		//Register.register(vase_rare);
		
		//Plater
		Registerer.registerWithTileEntity(FLOWER_POT, TileEntityFlowerPot.class);
		
		//Torch
		Registerer.register(FLORIC_TORCH_DANDELION);
		Registerer.register(FLORIC_TORCH_POPPY);
		Registerer.register(FLORIC_TORCH_BLUE_ORCHID);
		Registerer.register(FLORIC_TORCH_ALLIUM);
		Registerer.register(FLORIC_TORCH_AZURE_BLUET);
		Registerer.register(FLORIC_TORCH_RED_TULIP);
		Registerer.register(FLORIC_TORCH_ORANGE_TULIP);
		Registerer.register(FLORIC_TORCH_WHITE_TULIP);
		Registerer.register(FLORIC_TORCH_PINK_TULIP);
		Registerer.register(FLORIC_TORCH_OXEYE_DAISY);
		Registerer.register(FLORIC_TORCH_SUNFLOWER);
		Registerer.register(FLORIC_TORCH_LILAC);
		Registerer.register(FLORIC_TORCH_ROSE);
		Registerer.register(FLORIC_TORCH_PEONY);
		Registerer.register(FLORIC_TORCH_SAKURA);
		
		//Silage
		Registerer.register(ROUND_BALE_HAY);
		Registerer.registerWithMeta(ROUND_BALE_SILAGE);
		
		//Flower
		Registerer.register(LYCORIS);
		
		//Sakura
		Registerer.registerWithMetaWood(LEAVES_FLORIC_TYPE0);
		Registerer.registerWithMetaWood(SAPLING_FLORIC_TYPE0);
		
		//Salt
		Registerer.register(ORE_SALT);
		Registerer.register(DUST_SALT);
		Registerer.register(BLOCK_SALT);
		
		//Tool
		Registerer.register(PURNER);
		
		//PotionItem
		Registerer.register(BOTTLE_BROWN_EMPTY);
		Registerer.register(BOTTLE_BROWN_WATER);
		Registerer.register(BOTTLE_BROWN_FLOWER);
		
		//Events
		Registerer.register(CONFECTION_EVENT);
		
		//Christmas
		Registerer.registerWithMeta(LEAVES_CHRISTMAS);
		Registerer.register(LEAVES_CHRISTMAS_DYNAMIC);
		Registerer.register(LEAVES_CHRISTMAS_DYNAMIC_UNLIT);
		Registerer.register(LEAVES_CHRISTMAS_UNLIT);
		Registerer.registerWithMeta(ORNAMENT_CHRISTMAS);
		Registerer.registerWithMeta(SAPLING_CHRISTMAS);
		
		//Potion
		Registerer.register(POTION_FLORIC);
		Registerer.register(POTION_TEMPTATION);
		Registerer.register(POTION_TEMPTED);
		Registerer.register(POTION_ANTI_ZOMBIE);
		Registerer.register(POTION_ANTI_SKELETON);
		Registerer.register(POTION_ANTI_CREEPER);
		Registerer.register(POTION_ANTI_SPIDER);
		Registerer.register(POTION_ANTI_ENDERMAN);
		
		//Enchantment
		Registerer.register(ENCHANTMENT_FLORIC);

		//Biome
		int genBiomeWeight = ConfigChecker.getGenBiomeWeight();
		Registerer.register(BIOME_TULIP_LAND);
		Registerer.register(BIOME_ROSE_LAND);
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(BIOME_TULIP_LAND, genBiomeWeight));
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(BIOME_ROSE_LAND, genBiomeWeight));
		
		//Network
		NETWORK_GUI.registerMessage(PacketNBTGuiHandler.class, PacketNBTGui.class, 0, Side.CLIENT);
	}

	public static void registerRenders()
	{
		//Flower
		ItemMetaFlower.preRegisterRender(FLOWER_CUT);
		//ItemMetaFlower.preRegisterRender(flower_bouquet);
		ItemMetaFlower.preRegisterRender(PETAL_RAW);
		ItemMetaFlower.preRegisterRender(PETAL_DRY);
		ItemMetaFlower.preRegisterRender(PETAL_SALTY);
		ItemMetaFlower.preRegisterRender(PETAL_SUGARED);
		ItemMetaFlower.preRegisterRender(PETALS_RAW);
		ItemMetaFlower.preRegisterRender(PETALS_DRY);
		ItemMetaFlower.preRegisterRender(PETALS_SALTY);
		ItemMetaFlower.preRegisterRender(PETALS_SUGARED);
		
		ItemBlockMeta.preRegisterRender(STACK_DANDELION);
		ItemBlockMeta.preRegisterRender(STACK_POPPY);
		ItemBlockMeta.preRegisterRender(STACK_BLUE_ORCHID);
		ItemBlockMeta.preRegisterRender(STACK_ALLIUM);
		ItemBlockMeta.preRegisterRender(STACK_AZURE_BLUET);
		ItemBlockMeta.preRegisterRender(STACK_RED_TULIP);
		ItemBlockMeta.preRegisterRender(STACK_ORANGE_TULIP);
		ItemBlockMeta.preRegisterRender(STACK_WHITE_TULIP);
		ItemBlockMeta.preRegisterRender(STACK_PINK_TULIP);
		ItemBlockMeta.preRegisterRender(STACK_OXEYE_DAISY);
		ItemBlockMeta.preRegisterRender(STACK_SUNFLOWER);
		ItemBlockMeta.preRegisterRender(STACK_LILAC);
		ItemBlockMeta.preRegisterRender(STACK_ROSE);
		ItemBlockMeta.preRegisterRender(STACK_PEONY);
		ItemBlockMeta.preRegisterRender(STACK_SAKURA);

		Registerer.registerRender(STACK_DEAD);
		
		//Sachet
		Registerer.registerRender(SACHET);
		Registerer.registerRender(SACHET_FLOWER);
		Registerer.registerRender(SACHET_TEMPTATION);
		Registerer.registerRender(SACHET_ANTI_ZOMBIE);
		Registerer.registerRender(SACHET_ANTI_SKELETON);
		Registerer.registerRender(SACHET_ANTI_CREEPER);
		Registerer.registerRender(SACHET_ANTI_SPIDER);
		Registerer.registerRender(SACHET_ANTI_ENDERMAN);
		Registerer.registerRender(SACHET_HOLDER);
		
		//Basket
		Registerer.registerRender(BASKET_FLOWER);
		Registerer.registerRender(BASKET_LUNCH);

		//Crop
		//Hemp
		Registerer.registerRender(SEED_HEMP);
		Registerer.registerRender(HEMP_YARN);
		Registerer.registerRender(HEMP_TWINE);
		Registerer.registerRender(HEMP_CLOTH);
		
		//ClothArmor
		Registerer.registerRender(CLOTH_HELMET);
		Registerer.registerRender(CLOTH_CHESTPLATE);
		Registerer.registerRender(CLOTH_LEGGINGS);
		Registerer.registerRender(CLOTH_BOOTS);
		Registerer.registerRender(APRON_CHESTPLATE);

		//PotPourri
		//Register.registerRender(potpourri);
		
		//Doll
		Registerer.registerRender(DOLL_IRON_SIT);
		
		//Weather
		ItemBlockMeta.preRegisterRender(WEATHER_COCK);
		
		//Vase
		//Register.registerRender(vase_raw);
		//ItemMetaColor.preRegisterRender(vase_rare);

		//Planter
		Registerer.registerRender(FLOWER_POT);
		
		//Torch
		Registerer.registerRender(FLORIC_TORCH_DANDELION);
		Registerer.registerRender(FLORIC_TORCH_POPPY);
		Registerer.registerRender(FLORIC_TORCH_BLUE_ORCHID);
		Registerer.registerRender(FLORIC_TORCH_ALLIUM);
		Registerer.registerRender(FLORIC_TORCH_AZURE_BLUET);
		Registerer.registerRender(FLORIC_TORCH_RED_TULIP);
		Registerer.registerRender(FLORIC_TORCH_ORANGE_TULIP);
		Registerer.registerRender(FLORIC_TORCH_WHITE_TULIP);
		Registerer.registerRender(FLORIC_TORCH_PINK_TULIP);
		Registerer.registerRender(FLORIC_TORCH_OXEYE_DAISY);
		Registerer.registerRender(FLORIC_TORCH_SUNFLOWER);
		Registerer.registerRender(FLORIC_TORCH_LILAC);
		Registerer.registerRender(FLORIC_TORCH_ROSE);
		Registerer.registerRender(FLORIC_TORCH_PEONY);
		Registerer.registerRender(FLORIC_TORCH_SAKURA);

		//Silage
		Registerer.registerRender(ROUND_BALE_HAY);
		ItemBlockMeta.preRegisterRender(ROUND_BALE_SILAGE);
		
		//Flower
		Registerer.registerRender(LYCORIS);
		
		//Sakura
		ItemBlockMetaWood.preRegisterRender(LEAVES_FLORIC_TYPE0);
		ItemBlockMetaWood.preRegisterRender(SAPLING_FLORIC_TYPE0);
		
		//Salt
		Registerer.registerRender(ORE_SALT);
		Registerer.registerRender(DUST_SALT);
		Registerer.registerRender(BLOCK_SALT);
		
		//Tool
		Registerer.registerRender(PURNER);
		
		//PotionItem
		Registerer.registerRender(BOTTLE_BROWN_EMPTY);
		Registerer.registerRender(BOTTLE_BROWN_WATER);
		ItemMetaFlower.preRegisterRender(BOTTLE_BROWN_FLOWER);
		
		//Event
		ItemFoodConfection.preRegisterRender(CONFECTION_EVENT);
		
		//Christmas
		ItemBlockMeta.preRegisterRender(LEAVES_CHRISTMAS);
		Registerer.registerRender(LEAVES_CHRISTMAS_DYNAMIC);
		Registerer.registerRender(LEAVES_CHRISTMAS_DYNAMIC_UNLIT);
		Registerer.registerRender(LEAVES_CHRISTMAS_UNLIT);
		ItemBlockMeta.preRegisterRender(ORNAMENT_CHRISTMAS);
		ItemBlockMeta.preRegisterRender(SAPLING_CHRISTMAS);
	}
	
	public static void registeryOres()
	{
		//Vanilla
		OreDictionary.registerOre("dustSugar", Items.SUGAR);
		
		//Hemp
		OreDictionary.registerOre("fiberHemp", HEMP_YARN);
		OreDictionary.registerOre("seedHemp", SEED_HEMP);
		
		//Salt
		OreDictionary.registerOre("oreSalt", ORE_SALT);
		OreDictionary.registerOre("dustSalt", DUST_SALT);
		OreDictionary.registerOre("itemSalt", DUST_SALT);
		OreDictionary.registerOre("blockSalt", BLOCK_SALT);
	}
	
	public static void registeryModelBakeryStuffs()
	{
		//Flower
		ItemMetaFlower.preRegisteryModelBakeryStuff(FLOWER_CUT);
		//ItemMetaFlower.preRegisteryModelBakeryStuff(flower_bouquet);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETAL_RAW);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETAL_DRY);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETAL_SALTY);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETAL_SUGARED);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETALS_RAW);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETALS_DRY);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETALS_SALTY);
		ItemMetaFlower.preRegisteryModelBakeryStuff(PETALS_SUGARED);
		
		EnumDrying.preRegisteryModelBakeryStuff(STACK_DANDELION);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_POPPY);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_BLUE_ORCHID);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_ALLIUM);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_AZURE_BLUET);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_RED_TULIP);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_ORANGE_TULIP);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_WHITE_TULIP);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_PINK_TULIP);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_OXEYE_DAISY);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_SUNFLOWER);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_LILAC);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_ROSE);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_PEONY);
		EnumDrying.preRegisteryModelBakeryStuff(STACK_SAKURA);

		//Weather
		EnumWeatherCock.preRegisteryModelBakeryStuff(WEATHER_COCK);
		
		//Silage
		EnumDamaged.preRegisteryModelBakeryStuff(ROUND_BALE_SILAGE);

		//Sakure
		ItemBlockMetaWood.preRegisteryModelBakeryStuff(LEAVES_FLORIC_TYPE0);
		ItemBlockMetaWood.preRegisteryModelBakeryStuff(SAPLING_FLORIC_TYPE0);
		
		//Vase
		//ItemMetaColor.preRegisteryModelBakeryStuff(vase_rare);
		
		//PotionItem
		ItemMetaFlower.preRegisteryModelBakeryStuff(BOTTLE_BROWN_FLOWER);
		
		//Event
		ItemFoodConfection.preRegisteryModelBakeryStuff(CONFECTION_EVENT);
		
		//Christmas
		EnumVariant.preRegisteryModelBakeryStuff(LEAVES_CHRISTMAS);
		EnumVariant.preRegisteryModelBakeryStuff(ORNAMENT_CHRISTMAS);
		EnumVariant.preRegisteryModelBakeryStuff(SAPLING_CHRISTMAS);
	}
}
