package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class RecipeHandler {

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
}
