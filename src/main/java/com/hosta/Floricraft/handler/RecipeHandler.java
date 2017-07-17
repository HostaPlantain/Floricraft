package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHandler {
	
	public enum flowerRecipes
	{
		DANDELION	(0, new ItemStack(Blocks.YELLOW_FLOWER), FloricraftInit.STACK_DANDELION, FloricraftInit.FLORIC_TORCH_DANDELION),
		POPPY		(1, new ItemStack(Blocks.RED_FLOWER, 1, 0), FloricraftInit.STACK_POPPY, FloricraftInit.FLORIC_TORCH_POPPY),
		BLUE_ORCHID	(2, new ItemStack(Blocks.RED_FLOWER, 1, 1), FloricraftInit.STACK_BLUE_ORCHID, FloricraftInit.FLORIC_TORCH_BLUE_ORCHID),
		ALLIUM		(3, new ItemStack(Blocks.RED_FLOWER, 1, 2), FloricraftInit.STACK_ALLIUM, FloricraftInit.FLORIC_TORCH_ALLIUM),
		AZURE_BLUET	(4, new ItemStack(Blocks.RED_FLOWER, 1, 3), FloricraftInit.STACK_AZURE_BLUET, FloricraftInit.FLORIC_TORCH_AZURE_BLUET),
		RED_TULIP	(5, new ItemStack(Blocks.RED_FLOWER, 1, 4), FloricraftInit.STACK_RED_TULIP, FloricraftInit.FLORIC_TORCH_RED_TULIP),
		ORANGE_TULIP(6, new ItemStack(Blocks.RED_FLOWER, 1, 5), FloricraftInit.STACK_ORANGE_TULIP, FloricraftInit.FLORIC_TORCH_ORANGE_TULIP),
		WHITE_TULIP	(7, new ItemStack(Blocks.RED_FLOWER, 1, 6), FloricraftInit.STACK_WHITE_TULIP, FloricraftInit.FLORIC_TORCH_WHITE_TULIP),
		PINK_TULIP	(8, new ItemStack(Blocks.RED_FLOWER, 1, 7), FloricraftInit.STACK_PINK_TULIP, FloricraftInit.FLORIC_TORCH_PINK_TULIP),
		OXEYE_DAISY	(9, new ItemStack(Blocks.RED_FLOWER, 1, 8), FloricraftInit.STACK_OXEYE_DAISY, FloricraftInit.FLORIC_TORCH_OXEYE_DAISY),
		SUNFLOWER	(10, new ItemStack(Blocks.DOUBLE_PLANT, 1, 0), FloricraftInit.STACK_SUNFLOWER, FloricraftInit.FLORIC_TORCH_SUNFLOWER),
		LILAC		(11, new ItemStack(Blocks.DOUBLE_PLANT, 1, 1), FloricraftInit.STACK_LILAC, FloricraftInit.FLORIC_TORCH_LILAC),
		ROSE		(12, new ItemStack(Blocks.DOUBLE_PLANT, 1, 4), FloricraftInit.STACK_ROSE, FloricraftInit.FLORIC_TORCH_ROSE),
		PEONY		(13, new ItemStack(Blocks.DOUBLE_PLANT, 1, 5), FloricraftInit.STACK_PEONY, FloricraftInit.FLORIC_TORCH_PEONY),
		SAKURA		(14, new ItemStack(FloricraftInit.LEAVES_FLORIC_TYPE0, 1, 0), FloricraftInit.STACK_SAKURA, FloricraftInit.FLORIC_TORCH_SAKURA);
		
		protected static final flowerRecipes[] META_LOOKUP = new flowerRecipes[values().length];
		static
		{
			for (flowerRecipes flowerRecipes : values())
			{
				META_LOOKUP[flowerRecipes.meta] = flowerRecipes;
			}
		}
		
		protected int meta;
		protected ItemStack flower;
		protected Block stack;
		protected Block torch;
		
		private flowerRecipes(int meta, ItemStack flower, Block stack, Block torch)
		{
			this.meta = meta;
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
			ItemStack flower = flowerRecipes.flower;
			Block stack = flowerRecipes.stack;
			Block torch = flowerRecipes.torch;
			
			GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new Object[] {flower, new ItemStack(FloricraftInit.PURNER, 1, OreDictionary.WILDCARD_VALUE)});
			GameRegistry.addRecipe(new ItemStack(stack, 1, 0), new Object[] {"ccc", "ccc", "hch", 'c', new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), 'h', FloricraftInit.HEMP_TWINE});
			
			GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.PETAL_RAW, 1, i), new Object[] {new ItemStack(FloricraftInit.FLOWER_CUT, 1, i)});
			GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.PETAL_DRY, 6, i), new Object[] {new ItemStack(stack, 1, 3)});
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FloricraftInit.PETAL_SALTY, 1, i), new Object[] {new ItemStack(FloricraftInit.PETAL_RAW, 1, i), "dustSalt", "dustSalt"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FloricraftInit.PETAL_SUGARED, 1, i), new Object[] {new ItemStack(FloricraftInit.PETAL_RAW, 1, i), "dustSugar", "dustSugar"}));
			
			registerCompressRecipes(new ItemStack(FloricraftInit.PETALS_RAW, 1, i), new ItemStack(FloricraftInit.PETAL_RAW, 1, i));
			registerCompressRecipes(new ItemStack(FloricraftInit.PETALS_DRY, 1, i), new ItemStack(FloricraftInit.PETAL_DRY, 1, i));
			registerCompressRecipes(new ItemStack(FloricraftInit.PETALS_SALTY, 1, i), new ItemStack(FloricraftInit.PETAL_SALTY, 1, i));
			registerCompressRecipes(new ItemStack(FloricraftInit.PETALS_SUGARED, 1, i), new ItemStack(FloricraftInit.PETAL_SUGARED, 1, i));
			
			GameRegistry.addShapedRecipe(new ItemStack(torch), new Object[] {"c", "p", "s", 'c', Items.COAL, 'p', new ItemStack(FloricraftInit.PETALS_RAW, 1, i), 's', Items.STICK});
		}
		
		//Sachet
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET, 1), new Object[] {"ttt", "c c", " c ", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH});
		ItemStack petalsDry = new ItemStack(FloricraftInit.PETALS_DRY, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_FLOWER, 1), new Object[] {"ddd", "ddd", " s ", 'd', petalsDry, 's', FloricraftInit.SACHET});
		
		registerEncloseRecipes(new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(FloricraftInit.PETALS_RAW, 1, 13));
		registerEncloseRecipes(new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.ROTTEN_FLESH));
		registerEncloseRecipes(new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.BONE));
		registerEncloseRecipes(new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.GUNPOWDER));
		registerEncloseRecipes(new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.SPIDER_EYE));
		registerEncloseRecipes(new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1), new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.ENDER_PEARL));
		
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_FLOWER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_HOLDER, 1), new Object[] {"ttt", "t t", "sss", 't', FloricraftInit.HEMP_TWINE, 's', FloricraftInit.SACHET});
		
		//Basket
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.BASKET_FLOWER, 1), new Object[] {"tct", "cfc", "tct", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH, 'f', new ItemStack(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.BASKET_LUNCH, 1), new Object[] {"tct", "cfc", "tct", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH, 'f', Items.BREAD});
		
		//Hemp
		GameRegistry.addShapelessRecipe(new ItemStack(Items.STRING, 1), new Object[] {FloricraftInit.HEMP_YARN});
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(FloricraftInit.HEMP_TWINE, 1), new Object[] {"fiberHemp", "fiberHemp"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.HEMP_CLOTH, 1), new Object[] {"YYY", "YYY", 'Y', "fiberHemp"}));

		//Cloth_Armor
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.CLOTH_HELMET, 1), new Object[] {"ccc", "t t", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.CLOTH_CHESTPLATE, 1), new Object[] {"t t", "ccc", "ccc", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.CLOTH_LEGGINGS, 1), new Object[] {"ttt", "c c", "c c", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.CLOTH_BOOTS, 1), new Object[] {"t t", "c c", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.APRON_CHESTPLATE, 1), new Object[] {"t t", "tct", "tct", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});

		//PotPourri
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.POTPOURRI, 1), new Object[] {"ppp", "gsg", "ggg", 'p', Blocks.GLASS_PANE, 'g', Blocks.GLASS, 's', "itemSalt"}));

		//Doll
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.DOLL_IRON_SIT, 1), new Object[] {" p ", "iii", " i ", 'p', Blocks.PUMPKIN, 'i', "ingotIron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.DOLL_PLAYER, 1), new Object[] {" s ", "iii", " i ", 'p', "itemSkull", 'i', "ingotIron"}));

		//Wether
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.WEATHER_COCK, 1), new Object[] {" a ", " i ", "iii", 'a', Items.ARROW, 'i', "ingotIron"}));
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.WEATHER_COCK, 1, 0), new Object[] {new ItemStack(FloricraftInit.WEATHER_COCK, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.WEATHER_COCK, 1, 1), new Object[] {new ItemStack(FloricraftInit.WEATHER_COCK, 1, 0)});
		
		//Planter
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.FLOWER_POT, 1), new Object[] {Items.FLOWER_POT});
		
		//Silage
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.ROUND_BALE_HAY, 1), new Object[] {"tpt", "php", "tpt", 'h', Blocks.HAY_BLOCK, 't', FloricraftInit.HEMP_TWINE, 'p', Items.PAPER});
		
		//Salt
		registerBlockRecipes(FloricraftInit.BLOCK_SALT, FloricraftInit.DUST_SALT);
		
		//Tool
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.PURNER, 1, 0), new Object[] {" I", " I", "I ", 'I', "ingotIron"}));
		
		//Ballon
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.ITEM_BALLON, 1), new Object[] {"c", "t", 'c', FloricraftInit.HEMP_CLOTH, 't', FloricraftInit.HEMP_TWINE});
		for (int i = 0; i < 16; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.ITEM_BALLON, 1, i), new Object[] {new ItemStack(FloricraftInit.ITEM_BALLON, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.DYE, 1, i)});
		}
		
		//Christmas
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 0), new Object[] {"rtg", "tst", 't', Blocks.TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 0), new Object[] {"gtr", "tst", 't', Blocks.TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 1), new Object[] {"rtg", "tst", 't', Blocks.REDSTONE_TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 1), new Object[] {"gtr", "tst", 't', Blocks.REDSTONE_TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		
		ItemStack leavesChristmas = new ItemStack(FloricraftInit.LEAVES_CHRISTMAS, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 1, 0), new Object[] {"lll", "l l", "lll", 'l', leavesChristmas});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 1, 1), new Object[] {"wt", "rc", "cc", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH, 'w', new ItemStack(Items.DYE, 1, 15), 'r', new ItemStack(Items.DYE, 1, 1)});
		
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 16, 2), new Object[] {"ttt", "rgr", 't', FloricraftInit.HEMP_TWINE, 'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 16, 2), new Object[] {"ttt", "grg", 't', FloricraftInit.HEMP_TWINE, 'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		ItemStack dye = new ItemStack(Items.DYE, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 16, 3), new Object[] {"ttt", "ddd", 't', FloricraftInit.HEMP_TWINE, 'd', dye});
		}
	
	public static void registerFurnaceRecipes()
	{
		//GameRegistry.addSmelting(FloricraftInit.vase_raw, new ItemStack(FloricraftInit.vase_rare, 1, 15), 0);
	}
	
	public static void registerBrewingRecipes()
	{
		for(int i = 0; i < ItemMetaFlower.max_meta; i++)
		{
			BrewingRecipeRegistry.addRecipe(new ItemStack(FloricraftInit.BOTTLE_BROWN_WATER), new ItemStack(FloricraftInit.PETALS_RAW, 1, i), new ItemStack(FloricraftInit.BOTTLE_BROWN_FLOWER, 1, i));
		}
	}
	
	private static void registerBlockRecipes(Block block, Item item)
	{
		registerCompressRecipes(new ItemStack(block, 1), new ItemStack(item));
		GameRegistry.addShapelessRecipe(new ItemStack(item, 9), block);
	}
	
	private static void registerCompressRecipes(ItemStack itemCompressed, ItemStack itemStack)
	{
		GameRegistry.addRecipe(itemCompressed, new Object[] {"iii", "iii", "iii", 'i', itemStack});
	}
	
	private static void registerEncloseRecipes(ItemStack itemCompressed, ItemStack itemStack, ItemStack itemEnclose)
	{
		GameRegistry.addRecipe(itemCompressed, new Object[] {"eee", "eie", "eee", 'i', itemStack, 'e', itemEnclose});
	}
}
