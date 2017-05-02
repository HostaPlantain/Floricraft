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
	
	public static void registerCraftingRecipes()
	{
		//flower
		for(int i = 0; i < ItemMetaFlower.max_meta; i++)
		{
			ItemStack flower;
			Block stack;
			Block torch;
			switch(i)
			{
			default:
			case 0:
				flower = new ItemStack(Blocks.YELLOW_FLOWER);
				stack = FloricraftInit.STACK_DANDELION;
				torch = FloricraftInit.FLORIC_TORCH_DANDELION;
	    		break;
			case 1:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 0);
				stack = FloricraftInit.STACK_POPPY;
				torch = FloricraftInit.FLORIC_TORCH_POPPY;
	    		break;
			case 2:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 1);
				stack = FloricraftInit.STACK_BLUE_ORCHID;
				torch = FloricraftInit.FLORIC_TORCH_BLUE_ORCHID;
	    		break;
			case 3:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 2);
				stack = FloricraftInit.STACK_ALLIUM;
				torch = FloricraftInit.FLORIC_TORCH_ALLIUM;
	    		break;
			case 4:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 3);
				stack = FloricraftInit.STACK_AZURE_BLUET;
				torch = FloricraftInit.FLORIC_TORCH_AZURE_BLUET;
	    		break;
			case 5:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 4);
				stack = FloricraftInit.STACK_RED_TULIP;
				torch = FloricraftInit.FLORIC_TORCH_RED_TULIP;
	    		break;
			case 6:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 5);
				stack = FloricraftInit.STACK_ORANGE_TULIP;
				torch = FloricraftInit.FLORIC_TORCH_ORANGE_TULIP;
	    		break;
			case 7:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 6);
				stack = FloricraftInit.STACK_WHITE_TULIP;
				torch = FloricraftInit.FLORIC_TORCH_WHITE_TULIP;
	    		break;
			case 8:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 7);
				stack = FloricraftInit.STACK_PINK_TULIP;
				torch = FloricraftInit.FLORIC_TORCH_PINK_TULIP;
	    		break;
			case 9:
				flower = new ItemStack(Blocks.RED_FLOWER, 1, 8);
				stack = FloricraftInit.STACK_OXEYE_DAISY;
				torch = FloricraftInit.FLORIC_TORCH_OXEYE_DAISY;
				break;
			case 10:
				flower = new ItemStack(Blocks.DOUBLE_PLANT, 1, 0);
				stack = FloricraftInit.STACK_SUNFLOWER;
				torch = FloricraftInit.FLORIC_TORCH_SUNFLOWER;
	    		break;
			case 11:
				flower = new ItemStack(Blocks.DOUBLE_PLANT, 1, 1);
				stack = FloricraftInit.STACK_LILAC;
				torch = FloricraftInit.FLORIC_TORCH_LILAC;
	    		break;
			case 12:
				flower = new ItemStack(Blocks.DOUBLE_PLANT, 1, 4);
				stack = FloricraftInit.STACK_ROSE;
				torch = FloricraftInit.FLORIC_TORCH_ROSE;
	    		break;
			case 13:
				flower = new ItemStack(Blocks.DOUBLE_PLANT, 1, 5);
				stack = FloricraftInit.STACK_PEONY;
				torch = FloricraftInit.FLORIC_TORCH_PEONY;
				break;
			case 14:
				flower = new ItemStack(FloricraftInit.LEAVES_FLORIC_TYPE0, 1, 0);
				stack = FloricraftInit.STACK_SAKURA;
				torch = FloricraftInit.FLORIC_TORCH_SAKURA;
				break;
			}
			GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new Object[] {flower, new ItemStack(FloricraftInit.PURNER, 1, OreDictionary.WILDCARD_VALUE)});
			//GameRegistry.addRecipe(new ItemStack(FloricraftInit.flower_bouquet, 1, i), new Object[] {"sss", "sss", "hsh", 's', new ItemStack(stack, 1, 0), 'h', FloricraftInit.hemp_twine});
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
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET, 1), new Object[] {"ttt", "c c", "ccc", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH});
		ItemStack petalsDry = new ItemStack(FloricraftInit.PETALS_DRY, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_FLOWER, 1), new Object[] {"ddd", "ddd", " s ", 'd', petalsDry, 's', FloricraftInit.SACHET});
		
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1), new Object[] {"ppp", "psp", "ppp", 'p', new ItemStack(FloricraftInit.PETALS_RAW, 1, 13), 's', new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1), new Object[] {"fff", "fsf", "fff", 'f', Items.ROTTEN_FLESH, 's', new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1), new Object[] {"bbb", "bsb", "bbb", 'b', Items.BONE, 's', new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1), new Object[] {"ggg", "gsg", "ggg", 'g', Items.GUNPOWDER, 's', new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1), new Object[] {"eee", "ese", "eee", 'e', Items.SPIDER_EYE, 's', new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1), new Object[] {"ppp", "psp", "ppp", 'p', Items.ENDER_PEARL, 's', new ItemStack(FloricraftInit.SACHET_FLOWER, 1, OreDictionary.WILDCARD_VALUE)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_TEMPTATION, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_ZOMBIE, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_SKELETON, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_CREEPER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_SPIDER, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1), new Object[] {new ItemStack(FloricraftInit.SACHET_ANTI_ENDERMAN, 1, OreDictionary.WILDCARD_VALUE), petalsDry});
		
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

		//doll
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.DOLL_IRON_SIT, 1), new Object[] {" p ", "iii", " i ", 'p', Blocks.PUMPKIN, 'i', "ingotIron"}));
		
		//Vase
		/*for(int i = 1; i < 16; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.vase_rare, 1, i), new Object[] {new ItemStack(FloricraftInit.vase_rare, 1, 0), new ItemStack(Items.DYE, 1, i)});
		}*/
		
		//Planter
		GameRegistry.addShapelessRecipe(new ItemStack(FloricraftInit.FLOWER_POT, 1), new Object[] {Items.FLOWER_POT});
		
		//Silage
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.ROUND_BALE_HAY, 1), new Object[] {"tpt", "php", "tpt", 'h', Blocks.HAY_BLOCK, 't', FloricraftInit.HEMP_TWINE, 'p', Items.PAPER});
		
		//salt
		registerBlockRecipes(FloricraftInit.BLOCK_SALT, FloricraftInit.DUST_SALT);
		
		//Tool
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FloricraftInit.PURNER, 1, 0), new Object[] {" I", " I", "I ", 'I', "ingotIron"}));
		
		//Christmas
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 0), new Object[] {"rtg", "tst", 't', Blocks.TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 0), new Object[] {"gtr", "tst", 't', Blocks.TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 1), new Object[] {"rtg", "tst", 't', Blocks.REDSTONE_TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, 1), new Object[] {"gtr", "tst", 't', Blocks.REDSTONE_TORCH, 's', new ItemStack(Blocks.SAPLING, 1, 1),  'r', new ItemStack(Items.DYE, 1, 1), 'g', new ItemStack(Items.DYE, 1, 2)});
		ItemStack leavesChristmas = new ItemStack(FloricraftInit.LEAVES_CHRISTMAS, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 1, 0), new Object[] {"lll", "l l", "lll", 'l', leavesChristmas});
		GameRegistry.addShapedRecipe(new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, 1, 1), new Object[] {" t", " c", "cc", 't', FloricraftInit.HEMP_TWINE, 'c', FloricraftInit.HEMP_CLOTH});
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
	
	public static void registerBlockRecipes(Block block, Item item)
	{
		registerCompressRecipes(new ItemStack(block, 1), new ItemStack(item));
		GameRegistry.addShapelessRecipe(new ItemStack(item, 9), block);
	}
	
	public static void registerCompressRecipes(ItemStack itemCompressed, ItemStack itemStack)
	{
		GameRegistry.addRecipe(itemCompressed, new Object[] {"iii", "iii", "iii", 'i', itemStack});
	}
}
