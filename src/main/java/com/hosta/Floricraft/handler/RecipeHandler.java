package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.init.Registerer;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHandler {
	
	public enum flowerRecipes
	{
		DANDELION	(0, "dandelion", new ItemStack(Blocks.YELLOW_FLOWER), FloricraftInit.STACK_DANDELION, FloricraftInit.FLORIC_TORCH_DANDELION),
		POPPY		(1, "poppy", new ItemStack(Blocks.RED_FLOWER, 1, 0), FloricraftInit.STACK_POPPY, FloricraftInit.FLORIC_TORCH_POPPY),
		BLUE_ORCHID	(2, "blue_orchid", new ItemStack(Blocks.RED_FLOWER, 1, 1), FloricraftInit.STACK_BLUE_ORCHID, FloricraftInit.FLORIC_TORCH_BLUE_ORCHID),
		ALLIUM		(3, "allium", new ItemStack(Blocks.RED_FLOWER, 1, 2), FloricraftInit.STACK_ALLIUM, FloricraftInit.FLORIC_TORCH_ALLIUM),
		AZURE_BLUET	(4, "azure_bluet", new ItemStack(Blocks.RED_FLOWER, 1, 3), FloricraftInit.STACK_AZURE_BLUET, FloricraftInit.FLORIC_TORCH_AZURE_BLUET),
		RED_TULIP	(5, "red_tulip", new ItemStack(Blocks.RED_FLOWER, 1, 4), FloricraftInit.STACK_RED_TULIP, FloricraftInit.FLORIC_TORCH_RED_TULIP),
		ORANGE_TULIP(6, "orange_tulip", new ItemStack(Blocks.RED_FLOWER, 1, 5), FloricraftInit.STACK_ORANGE_TULIP, FloricraftInit.FLORIC_TORCH_ORANGE_TULIP),
		WHITE_TULIP	(7, "white_tulip", new ItemStack(Blocks.RED_FLOWER, 1, 6), FloricraftInit.STACK_WHITE_TULIP, FloricraftInit.FLORIC_TORCH_WHITE_TULIP),
		PINK_TULIP	(8, "pink_tulip", new ItemStack(Blocks.RED_FLOWER, 1, 7), FloricraftInit.STACK_PINK_TULIP, FloricraftInit.FLORIC_TORCH_PINK_TULIP),
		OXEYE_DAISY	(9, "oxeye_daisy", new ItemStack(Blocks.RED_FLOWER, 1, 8), FloricraftInit.STACK_OXEYE_DAISY, FloricraftInit.FLORIC_TORCH_OXEYE_DAISY),
		SUNFLOWER	(10, "sunflower", new ItemStack(Blocks.DOUBLE_PLANT, 1, 0), FloricraftInit.STACK_SUNFLOWER, FloricraftInit.FLORIC_TORCH_SUNFLOWER),
		LILAC		(11, "lilac", new ItemStack(Blocks.DOUBLE_PLANT, 1, 1), FloricraftInit.STACK_LILAC, FloricraftInit.FLORIC_TORCH_LILAC),
		ROSE		(12, "rose", new ItemStack(Blocks.DOUBLE_PLANT, 1, 4), FloricraftInit.STACK_ROSE, FloricraftInit.FLORIC_TORCH_ROSE),
		PEONY		(13, "peony", new ItemStack(Blocks.DOUBLE_PLANT, 1, 5), FloricraftInit.STACK_PEONY, FloricraftInit.FLORIC_TORCH_PEONY),
		SAKURA		(14, "sakura", new ItemStack(FloricraftInit.LEAVES_FLORIC_TYPE0, 1, 0), FloricraftInit.STACK_SAKURA, FloricraftInit.FLORIC_TORCH_SAKURA);
		
		protected static final flowerRecipes[] META_LOOKUP = new flowerRecipes[values().length];
		static
		{
			for (flowerRecipes flowerRecipes : values())
			{
				META_LOOKUP[flowerRecipes.meta] = flowerRecipes;
			}
		}
		
		protected int meta;
		protected String name;
		protected ItemStack flower;
		protected Block stack;
		protected Block torch;
		
		private flowerRecipes(int meta, String name, ItemStack flower, Block stack, Block torch)
		{
			this.meta = meta;
			this.name = name;
			this.flower = flower;
			this.stack = stack;
			this.torch = torch;
		}
	}
	
	public static void registerCraftingRecipes()
	{
		//flower
		for (flowerRecipes flowerRecipes : flowerRecipes.META_LOOKUP)
		{
			int i = flowerRecipes.meta;
			String name = flowerRecipes.name;
			ItemStack flower = flowerRecipes.flower;
			Block stack = flowerRecipes.stack;
			Block torch = flowerRecipes.torch;
			
			registerShapelessRecipes("flower_cut_" + name, new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new Object[] {flower, new ItemStack(FloricraftInit.PURNER, 1, OreDictionary.WILDCARD_VALUE)});
			registerShapedRecipes("flower_stack_" + name, new ItemStack(stack, 1, 0), new Object[] {"ccc", "ccc", "hch", 'c', new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), 'h', FloricraftInit.HEMP_TWINE});
			
			registerShapelessRecipes("petal_raw_" + name, new ItemStack(FloricraftInit.PETAL_RAW, 1, i), new Object[] {new ItemStack(FloricraftInit.FLOWER_CUT, 1, i)});
			registerShapelessRecipes("petal_dry_" + name, new ItemStack(FloricraftInit.PETAL_DRY, 6, i), new Object[] {new ItemStack(stack, 1, 3)});
			registerShapelessRecipes("petal_salty_" + name, new ItemStack(FloricraftInit.PETAL_SALTY, 1, i), new Object[] {new ItemStack(FloricraftInit.PETAL_RAW, 1, i), "dustSalt", "dustSalt"});
			registerShapelessRecipes("petal_sugared_" + name, new ItemStack(FloricraftInit.PETAL_SUGARED, 1, i), new Object[] {new ItemStack(FloricraftInit.PETAL_RAW, 1, i), "dustSugar", "dustSugar"});
			
			registerCompressRecipes("petals_raw_" + name, new ItemStack(FloricraftInit.PETALS_RAW, 1, i), new ItemStack(FloricraftInit.PETAL_RAW, 1, i));
			registerCompressRecipes("petals_dry_" + name, new ItemStack(FloricraftInit.PETALS_DRY, 1, i), new ItemStack(FloricraftInit.PETAL_DRY, 1, i));
			registerCompressRecipes("petals_salty_" + name, new ItemStack(FloricraftInit.PETALS_SALTY, 1, i), new ItemStack(FloricraftInit.PETAL_SALTY, 1, i));
			registerCompressRecipes("petals_sugared_" + name, new ItemStack(FloricraftInit.PETALS_SUGARED, 1, i), new ItemStack(FloricraftInit.PETAL_SUGARED, 1, i));
			
			registerShapedRecipes("floric_torch_" + name, new ItemStack(torch), new Object[] {"c", "p", "s", 'c', Items.COAL, 'p', new ItemStack(FloricraftInit.PETALS_RAW, 1, i), 's', Items.STICK});
		}
		
		//Sachet
		registerShapedRecipes("sachet_sac", new ItemStack(FloricraftInit.SACHET, 1), new Object[] {"ttt", "c c", " c ", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH});
		ItemStack petalsDry = new ItemStack(FloricraftInit.PETALS_DRY, 1, OreDictionary.WILDCARD_VALUE);
		registerShapedRecipes("sachet_flower", new ItemStack(FloricraftInit.SACHET_FLOWER, 1), new Object[] {"ddd", "ddd", " s ", 'd', petalsDry, 's', FloricraftInit.SACHET});

		registerEncloseRecipes("sachet_temptation", new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(FloricraftInit.PETALS_RAW, 1, 13));
		registerEncloseRecipes("sachet_anti_zombie", new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.ROTTEN_FLESH));
		registerEncloseRecipes("sachet_anti_skeleton", new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.BONE));
		registerEncloseRecipes("sachet_anti_creeper", new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.GUNPOWDER));
		registerEncloseRecipes("sachet_anti_spider", new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SPIDER_EYE));
		registerEncloseRecipes("sachet_anti_enderman", new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.ENDER_PEARL));

		registerShapelessRecipes("repair_sachet_flower", new ItemStack(FloricraftInit.SACHET_FLOWER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		registerShapelessRecipes("repair_sachet_temptation", new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		registerShapelessRecipes("repair_sachet_anti_zombie", new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		registerShapelessRecipes("repair_sachet_anti_skeleton", new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		registerShapelessRecipes("repair_sachet_anti_creeper", new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		registerShapelessRecipes("repair_sachet_anti_spider", new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		registerShapelessRecipes("repair_sachet_anti_enderman", new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		
		registerShapedRecipes("sachet_holder", new ItemStack(FloricraftInit.SACHET_HOLDER, 1), new Object[] {"ttt", "t t", "sss", 't', FloricraftInit.HEMP_TWINE, 's', FloricraftInit.SACHET});
		
		//Basket
		registerShapedRecipes("basket_flower", new ItemStack(FloricraftInit.BASKET_FLOWER, 1), new Object[] {"tct", "cfc", "tct", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH, 'f', new ItemStack(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		registerShapedRecipes("basket_lunch", new ItemStack(FloricraftInit.BASKET_LUNCH, 1), new Object[] {"tct", "cfc", "tct", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH, 'f', Items.BREAD});
		
		//Hemp
		registerShapelessRecipes("string", new ItemStack(Items.STRING, 1), new Object[] {FloricraftInit.HEMP_YARN});
		registerShapelessRecipes("hemp_twine", new ItemStack(FloricraftInit.HEMP_TWINE, 1), new Object[] {"fiberHemp", "fiberHemp"});
		registerShapedRecipes("hemp_cloth", new ItemStack(FloricraftInit.HEMP_CLOTH, 1), new Object[] {"YYY", "YYY", 'Y', "fiberHemp"});

		//Cloth_Armor
		registerShapedRecipes("cloth_helmet", new ItemStack(FloricraftInit.CLOTH_HELMET, 1), new Object[] {"ccc", "t t", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		registerShapedRecipes("cloth_chestplate", new ItemStack(FloricraftInit.CLOTH_CHESTPLATE, 1), new Object[] {"t t", "ccc", "ccc", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		registerShapedRecipes("cloth_leggings", new ItemStack(FloricraftInit.CLOTH_LEGGINGS, 1), new Object[] {"ttt", "c c", "c c", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		registerShapedRecipes("cloth_boots", new ItemStack(FloricraftInit.CLOTH_BOOTS, 1), new Object[] {"t t", "c c", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		registerShapedRecipes("apron_chestplate", new ItemStack(FloricraftInit.APRON_CHESTPLATE, 1), new Object[] {"t t", "tct", "tct", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});

		//PotPourri
		registerShapedRecipes("potpourri", new ItemStack(FloricraftInit.POTPOURRI, 1), new Object[] {"ppp", "gsg", "ggg", 'p', Blocks.GLASS_PANE, 'g', Blocks.GLASS, 's', "itemSalt"});

		//Doll
		registerShapedRecipes("doll_iron_sit", new ItemStack(FloricraftInit.DOLL_IRON_SIT, 1), new Object[] {" p ", "iii", " i ", 'p', Blocks.PUMPKIN, 'i', "ingotIron"});

		//Wether
		registerShapedRecipes("weather_cock", new ItemStack(FloricraftInit.WEATHER_COCK, 1), new Object[] {" a ", " i ", "iii", 'a', Items.ARROW, 'i', "ingotIron"});
		registerShapelessRecipes("weather_cock_cock", new ItemStack(FloricraftInit.WEATHER_COCK, 1, 0), new Object[] {new ItemStack(FloricraftInit.WEATHER_COCK, 1, 1)});
		registerShapelessRecipes("weather_cock_dog", new ItemStack(FloricraftInit.WEATHER_COCK, 1, 1), new Object[] {new ItemStack(FloricraftInit.WEATHER_COCK, 1, 0)});
		
		//Planter
		registerShapelessRecipes("flower_pot", new ItemStack(FloricraftInit.FLOWER_POT, 1), new Object[] {Items.FLOWER_POT});
		
		//Silage
		registerShapedRecipes("round_bale_hay", new ItemStack(FloricraftInit.ROUND_BALE_HAY, 1), new Object[] {"tpt", "php", "tpt", 'h', Blocks.HAY_BLOCK, 't', FloricraftInit.HEMP_TWINE, 'p', Items.PAPER});
		
		//Salt
		registerCompressRecipes("block_salt", new ItemStack(FloricraftInit.BLOCK_SALT), new ItemStack(FloricraftInit.DUST_SALT));
		registerShapelessRecipes("dust_salt", new ItemStack(FloricraftInit.DUST_SALT, 9), new Object[] {FloricraftInit.BLOCK_SALT});
		
		//Tool
		registerShapedRecipes("purner", new ItemStack(FloricraftInit.PURNER, 1, 0), new Object[] {" I", " I", "I ", 'I', "ingotIron"});
		
		//Christmas
		registerShapedRecipes("sapling_christmas_normal_1", new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 0), new Object[] {"rtg", "tst", 't', Blocks.TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		registerShapedRecipes("sapling_christmas_normal_2", new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 0), new Object[] {"gtr", "tst", 't', Blocks.TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		registerShapedRecipes("sapling_christmas_twinkling_1", new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 1), new Object[] {"rtg", "tst", 't', Blocks.REDSTONE_TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		registerShapedRecipes("sapling_christmas_twinkling_2", new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 1), new Object[] {"gtr", "tst", 't', Blocks.REDSTONE_TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		
		ItemStack leavesChristmas = new ItemStack(FloricraftInit.LEAVES_CHRISTMAS, 1, OreDictionary.WILDCARD_VALUE);
		registerShapedRecipes("christmas_wreath", new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 1, 0), new Object[] {"lll", "l l", "lll", 'l', leavesChristmas});
		registerShapedRecipes("christmas_sox", new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 1, 1), new Object[] {"wt", "rc", "cc", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH, 'w', new ItemStack(Items.DYE, 1, 15), 'r', new ItemStack(Items.DYE, 1, 1)});
		
		registerShapedRecipes("flag_garland_christmas_1", new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 16, 2), new Object[] {"ttt", "rgr", 't', FloricraftInit.HEMP_TWINE, 'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		registerShapedRecipes("flag_garland_christmas_2", new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 16, 2), new Object[] {"ttt", "grg", 't', FloricraftInit.HEMP_TWINE, 'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		ItemStack dye = new ItemStack(Items.DYE, 1, OreDictionary.WILDCARD_VALUE);
		registerShapedRecipes("flag_garland_colorful", new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 16, 3), new Object[] {"ttt", "ddd", 't', FloricraftInit.HEMP_TWINE, 'd', dye});
	}
	
	public static void registerFurnaceRecipes()
	{

	}
	
	public static void registerBrewingRecipes()
	{
		for(int i = 0; i < ItemMetaFlower.max_meta; i++)
		{
			BrewingRecipeRegistry.addRecipe(new ItemStack(FloricraftInit.BOTTLE_BROWN_WATER), new ItemStack(FloricraftInit.PETALS_RAW, 1, i), new ItemStack(FloricraftInit.BOTTLE_BROWN_FLOWER, 1, i));
		}
	}

	private static void registerRecipes(String ID, IRecipe recipe)
	{
		Registerer.register(recipe.setRegistryName(new ResourceLocation(Reference.MOD_ID, ID)));
	}

	private static void registerShapedRecipes(String ID, ItemStack resalt, Object[] recipe)
	{
		registerRecipes(ID, new ShapedOreRecipe(new ResourceLocation(Reference.MOD_ID, ID), resalt, recipe));
	}

	private static void registerShapelessRecipes(String ID, ItemStack resalt, Object[] recipe)
	{
		registerRecipes(ID, new ShapelessOreRecipe(new ResourceLocation(Reference.MOD_ID, ID), resalt, recipe));
	}
	
	private static void registerCompressRecipes(String ID, ItemStack resalt, ItemStack item)
	{
		registerShapedRecipes(ID, resalt, new Object[] {"iii", "iii", "iii", 'i', item});
	}
	
	private static void registerEncloseRecipes(String ID, ItemStack resalt, ItemStack center, ItemStack item)
	{
		registerShapedRecipes(ID, resalt, new Object[] {"iii", "ici", "iii", 'c', center, 'i', item});
	}
}
