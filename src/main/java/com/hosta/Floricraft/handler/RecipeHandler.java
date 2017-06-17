package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;
import com.hosta.Floricraft.item.crafting.RecipeBasic;
import com.hosta.Floricraft.item.crafting.RecipeBasicShapeless;
import com.hosta.Floricraft.item.crafting.RecipeBasicShapelessWithMeta;
import com.hosta.Floricraft.item.crafting.RecipeBasicWithMeta;
import com.hosta.Floricraft.item.crafting.RecipeSachet;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
			Item stack = Item.getItemFromBlock(flowerRecipes.stack);
			Block torch = flowerRecipes.torch;
			
			registerFloricRecipes("flower_cut_" + name, new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack[] {flower, new ItemStack(FloricraftInit.PURNER, 1, OreDictionary.WILDCARD_VALUE)}));
			registerFloricRecipes("flower_stack_" + name, new RecipeBasicWithMeta(stack, new ItemStack[] {new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.HEMP_TWINE), new ItemStack(FloricraftInit.FLOWER_CUT, 1, i), new ItemStack(FloricraftInit.HEMP_TWINE)}));

			registerFloricRecipes("petal_raw_" + name, new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.PETAL_RAW, 1, i), new ItemStack[] {new ItemStack(FloricraftInit.FLOWER_CUT, 1, i)}));
			registerFloricRecipes("petal_dry_" + name, new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.PETAL_DRY, 6, i), new ItemStack[] {new ItemStack(stack, 1, 3)}));
			registerFloricRecipes("petal_salty_" + name, new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.PETAL_SALTY, 1, i), new ItemStack[] {new ItemStack(FloricraftInit.PETAL_RAW, 1, i), new ItemStack(FloricraftInit.DUST_SALT), new ItemStack(FloricraftInit.DUST_SALT)}));
			registerFloricRecipes("petal_sugared_" + name, new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.PETAL_SUGARED, 1, i), new ItemStack[] {new ItemStack(FloricraftInit.PETAL_RAW, 1, i), new ItemStack(Items.SUGAR), new ItemStack(Items.SUGAR)}));

			registerCompressRecipes("petals_raw_" + name, new ItemStack(FloricraftInit.PETALS_RAW, 1, i), new ItemStack(FloricraftInit.PETAL_RAW, 1, i));
			registerCompressRecipes("petals_dry_" + name, new ItemStack(FloricraftInit.PETALS_DRY, 1, i), new ItemStack(FloricraftInit.PETAL_DRY, 1, i));
			registerCompressRecipes("petals_salty_" + name, new ItemStack(FloricraftInit.PETALS_SALTY, 1, i), new ItemStack(FloricraftInit.PETAL_SALTY, 1, i));
			registerCompressRecipes("petals_sugared_" + name, new ItemStack(FloricraftInit.PETALS_SUGARED, 1, i), new ItemStack(FloricraftInit.PETAL_SUGARED, 1, i));

			registerFloricRecipes("floric_torch_" + name + "1", new RecipeBasicWithMeta(torch, new ItemStack[] {new ItemStack(Items.COAL), ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(FloricraftInit.PETALS_RAW, 1, i), ItemStack.EMPTY, ItemStack.EMPTY,  new ItemStack(Items.STICK), ItemStack.EMPTY, ItemStack.EMPTY}));
			registerFloricRecipes("floric_torch_" + name + "2", new RecipeBasicWithMeta(torch, new ItemStack[] {ItemStack.EMPTY, new ItemStack(Items.COAL), ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(FloricraftInit.PETALS_RAW, 1, i), ItemStack.EMPTY, ItemStack.EMPTY,  new ItemStack(Items.STICK), ItemStack.EMPTY}));
			registerFloricRecipes("floric_torch_" + name + "3", new RecipeBasicWithMeta(torch, new ItemStack[] {ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(Items.COAL), ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(FloricraftInit.PETALS_RAW, 1, i), ItemStack.EMPTY, ItemStack.EMPTY,  new ItemStack(Items.STICK)}));
		}

		//Sachet
		registerFloricRecipes("sachet_sac", new RecipeBasic(FloricraftInit.SACHET, new Item[] {FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, null, FloricraftInit.HEMP_CLOTH, null, FloricraftInit.HEMP_CLOTH, null}));
		
		registerFloricRecipes("sachet_flower", new RecipeBasic(FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.PETALS_DRY, FloricraftInit.PETALS_DRY, FloricraftInit.PETALS_DRY, FloricraftInit.PETALS_DRY, FloricraftInit.PETALS_DRY, FloricraftInit.PETALS_DRY, null, FloricraftInit.SACHET, null}));

		registerFloricRecipes("sachet_temptation", new RecipeSachet(FloricraftInit.SACHET_TEMPTATION, FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW, FloricraftInit.PETALS_RAW}));
		registerFloricRecipes("sachet_anti_zombie", new RecipeSachet(FloricraftInit.SACHET_ANTI_ZOMBIE, FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH, Items.ROTTEN_FLESH}));
		registerFloricRecipes("sachet_anti_skeleton", new RecipeSachet(FloricraftInit.SACHET_ANTI_SKELETON, FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, Items.BONE, Items.BONE, Items.BONE, Items.BONE, Items.BONE, Items.BONE, Items.BONE, Items.BONE}));
		registerFloricRecipes("sachet_anti_creeper", new RecipeSachet(FloricraftInit.SACHET_ANTI_CREEPER, FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER}));
		registerFloricRecipes("sachet_anti_spider", new RecipeSachet(FloricraftInit.SACHET_ANTI_SPIDER, FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, Items.SPIDER_EYE, Items.SPIDER_EYE, Items.SPIDER_EYE, Items.SPIDER_EYE, Items.SPIDER_EYE, Items.SPIDER_EYE, Items.SPIDER_EYE, Items.SPIDER_EYE}));
		registerFloricRecipes("sachet_anti_enderman", new RecipeSachet(FloricraftInit.SACHET_ANTI_ENDERMAN, FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL}));

		registerFloricRecipes("repair_sachet_flower", new RecipeBasicShapeless(FloricraftInit.SACHET_FLOWER, new Item[] {FloricraftInit.SACHET_FLOWER, FloricraftInit.PETALS_DRY}));
		registerFloricRecipes("repair_sachet_temptation", new RecipeBasicShapeless(FloricraftInit.SACHET_TEMPTATION, new Item[] {FloricraftInit.SACHET_TEMPTATION, FloricraftInit.PETALS_DRY}));
		registerFloricRecipes("repair_sachet_anti_zombie", new RecipeBasicShapeless(FloricraftInit.SACHET_ANTI_ZOMBIE, new Item[] {FloricraftInit.SACHET_ANTI_ZOMBIE, FloricraftInit.PETALS_DRY}));
		registerFloricRecipes("repair_sachet_anti_skeleton", new RecipeBasicShapeless(FloricraftInit.SACHET_ANTI_SKELETON, new Item[] {FloricraftInit.SACHET_ANTI_SKELETON, FloricraftInit.PETALS_DRY}));
		registerFloricRecipes("repair_sachet_anti_creeper", new RecipeBasicShapeless(FloricraftInit.SACHET_ANTI_CREEPER, new Item[] {FloricraftInit.SACHET_ANTI_CREEPER, FloricraftInit.PETALS_DRY}));
		registerFloricRecipes("repair_sachet_anti_spider", new RecipeBasicShapeless(FloricraftInit.SACHET_ANTI_SPIDER, new Item[] {FloricraftInit.SACHET_ANTI_SPIDER, FloricraftInit.PETALS_DRY}));
		registerFloricRecipes("repair_sachet_anti_enderman", new RecipeBasicShapeless(FloricraftInit.SACHET_ANTI_ENDERMAN, new Item[] {FloricraftInit.SACHET_ANTI_ENDERMAN, FloricraftInit.PETALS_DRY}));

		registerFloricRecipes("sachet_holder", new RecipeBasic(FloricraftInit.SACHET_HOLDER, new Item[] {FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE, FloricraftInit.SACHET, FloricraftInit.SACHET, FloricraftInit.SACHET}));

		//Basket
		registerFloricRecipes("basket_flower", new RecipeBasic(FloricraftInit.BASKET_FLOWER, new Item[] {FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, Item.getItemFromBlock(Blocks.RED_FLOWER), FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE}));
		registerFloricRecipes("basket_lunch", new RecipeBasic(FloricraftInit.BASKET_LUNCH, new Item[] {FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, Items.BREAD, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE}));

		//Hemp
		registerFloricRecipes("string", new RecipeBasicShapeless(Items.STRING, new Item[] {FloricraftInit.HEMP_YARN}));
		registerFloricRecipes("hemp_twine", new RecipeBasicShapeless(FloricraftInit.HEMP_TWINE, new Item[] {FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN}));
		registerFloricRecipes("hemp_cloth1", new RecipeBasic(FloricraftInit.HEMP_CLOTH, new Item[] {FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, null, null, null}));
		registerFloricRecipes("hemp_cloth2", new RecipeBasic(FloricraftInit.HEMP_CLOTH, new Item[] {null, null, null, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN, FloricraftInit.HEMP_YARN}));

		//Cloth_Armor
		registerFloricRecipes("cloth_helmet1", new RecipeBasic(FloricraftInit.CLOTH_HELMET, new Item[] {FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE, null, null, null}));
		registerFloricRecipes("cloth_helmet2", new RecipeBasic(FloricraftInit.CLOTH_HELMET, new Item[] {null, null, null, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE}));
		registerFloricRecipes("cloth_chestplate", new RecipeBasic(FloricraftInit.CLOTH_CHESTPLATE, new Item[] {FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH}));
		registerFloricRecipes("cloth_leggings", new RecipeBasic(FloricraftInit.CLOTH_LEGGINGS, new Item[] {FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, null, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_CLOTH, null, FloricraftInit.HEMP_CLOTH}));
		registerFloricRecipes("cloth_boots1", new RecipeBasic(FloricraftInit.CLOTH_BOOTS, new Item[] {FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, null, FloricraftInit.HEMP_CLOTH, null, null, null}));
		registerFloricRecipes("cloth_boots2", new RecipeBasic(FloricraftInit.CLOTH_BOOTS, new Item[] {null, null, null, FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, null, FloricraftInit.HEMP_CLOTH}));

		registerFloricRecipes("apron_chestplate", new RecipeBasic(FloricraftInit.APRON_CHESTPLATE, new Item[] {FloricraftInit.HEMP_TWINE, null, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_TWINE, FloricraftInit.HEMP_CLOTH, FloricraftInit.HEMP_TWINE}));

		//PotPourri
		registerFloricRecipes("potpourri", new RecipeBasic(FloricraftInit.POTPOURRI, new Item[] {Item.getItemFromBlock(Blocks.GLASS_PANE), Item.getItemFromBlock(Blocks.GLASS_PANE), Item.getItemFromBlock(Blocks.GLASS_PANE), Item.getItemFromBlock(Blocks.GLASS), FloricraftInit.DUST_SALT, Item.getItemFromBlock(Blocks.GLASS), Item.getItemFromBlock(Blocks.GLASS), Item.getItemFromBlock(Blocks.GLASS), Item.getItemFromBlock(Blocks.GLASS)}));

		//Doll
		registerFloricRecipes("doll_iron_sit", new RecipeBasic(FloricraftInit.DOLL_IRON_SIT, new Item[] {null, Item.getItemFromBlock(Blocks.PUMPKIN), null, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, null, Items.IRON_INGOT, null}));

		//Wether
		registerFloricRecipes("weather_cock", new RecipeBasic(FloricraftInit.WEATHER_COCK, new Item[] {null, Items.ARROW, null, null, Items.IRON_INGOT, null, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT}));
		registerFloricRecipes("weather_cock_dog", new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.WEATHER_COCK, 1, 1), new ItemStack[] {new ItemStack(FloricraftInit.WEATHER_COCK, 1, 0)}));
		registerFloricRecipes("weather_cock_cock", new RecipeBasicShapelessWithMeta(new ItemStack(FloricraftInit.WEATHER_COCK, 1, 0), new ItemStack[] {new ItemStack(FloricraftInit.WEATHER_COCK, 1, 1)}));

		//Planter
		registerFloricRecipes("flower_pot", new RecipeBasicShapeless(FloricraftInit.FLOWER_POT, new Item[] {Items.FLOWER_POT}));

		//Silage
		registerFloricRecipes("round_bale_hay", new RecipeBasic(FloricraftInit.ROUND_BALE_HAY, new Item[] {FloricraftInit.HEMP_TWINE, Items.PAPER, FloricraftInit.HEMP_TWINE, Items.PAPER, Item.getItemFromBlock(Blocks.HAY_BLOCK), Items.PAPER, FloricraftInit.HEMP_TWINE, Items.PAPER, FloricraftInit.HEMP_TWINE}));

		//Salt
		registerCompressRecipes("block_salt", new ItemStack(FloricraftInit.BLOCK_SALT), new ItemStack(FloricraftInit.DUST_SALT));
		registerFloricRecipes("dust_salt", new RecipeBasicShapeless(new ItemStack(FloricraftInit.DUST_SALT, 9), new Item[] {Item.getItemFromBlock(FloricraftInit.BLOCK_SALT)}));
		
		//Tool
		registerFloricRecipes("purner1", new RecipeBasic(FloricraftInit.PURNER, new Item[] {null, null, Items.IRON_INGOT, null, null, Items.IRON_INGOT, null, Items.IRON_INGOT, null}));
		registerFloricRecipes("purner2", new RecipeBasic(FloricraftInit.PURNER, new Item[] {null, Items.IRON_INGOT, null, null, Items.IRON_INGOT, null, Items.IRON_INGOT, null, null}));
		
		//Christmas
		
		
		
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
	
	private static void registerFloricRecipes(String ID, IRecipe recipe)
	{
		//CraftingManager.func_193372_a(new ResourceLocation(ID, Reference.MOD_ID), recipe);
		CraftingManager.field_193380_a.register(CraftingManager.field_193380_a.getKeys().size(), new ResourceLocation(ID, Reference.MOD_ID), recipe);
	}
	
	private static void registerCompressRecipes(String ID, ItemStack itemCompressed, ItemStack item)
	{
		registerFloricRecipes(ID, new RecipeBasicShapelessWithMeta(itemCompressed, new ItemStack[] {item, item, item, item, item, item, item, item, item}));
	}
}
