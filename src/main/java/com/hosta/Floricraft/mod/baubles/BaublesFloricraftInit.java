package com.hosta.Floricraft.mod.baubles;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BaublesFloricraftInit {

	//Amulet Sachet
	public static final Item AMULET_SACHET_FLOWER = new BaublesItemMetaAmuletSachet("amulet_sachet_flower"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_TEMPTATION = new BaublesItemMetaAmuletSachet("amulet_sachet_temptation"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_ZOMBIE = new BaublesItemMetaAmuletSachet("amulet_sachet_anti_zombie"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_SKELETON = new BaublesItemMetaAmuletSachet("amulet_sachet_anti_skeleton"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_CREEPER = new BaublesItemMetaAmuletSachet("amulet_sachet_anti_creeper"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_SPIDER = new BaublesItemMetaAmuletSachet("amulet_sachet_anti_spider"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_ENDERMAN = new BaublesItemMetaAmuletSachet("amulet_sachet_anti_enderman"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	
	public static void init()
	{
		
	}
	
	public static void registers()
	{
		//Amulet Sachet
		Registerer.register(AMULET_SACHET_FLOWER);
		Registerer.register(AMULET_SACHET_TEMPTATION);
		Registerer.register(AMULET_SACHET_ANTI_ZOMBIE);
		Registerer.register(AMULET_SACHET_ANTI_SKELETON);
		Registerer.register(AMULET_SACHET_ANTI_CREEPER);
		Registerer.register(AMULET_SACHET_ANTI_SPIDER);
		Registerer.register(AMULET_SACHET_ANTI_ENDERMAN);
	}
	
	public static void registerRenders()
	{
		//Amulet Sachet
		Registerer.registerRender(AMULET_SACHET_FLOWER);
		Registerer.registerRender(AMULET_SACHET_TEMPTATION);
		Registerer.registerRender(AMULET_SACHET_ANTI_ZOMBIE);
		Registerer.registerRender(AMULET_SACHET_ANTI_SKELETON);
		Registerer.registerRender(AMULET_SACHET_ANTI_CREEPER);
		Registerer.registerRender(AMULET_SACHET_ANTI_SPIDER);
		Registerer.registerRender(AMULET_SACHET_ANTI_ENDERMAN);
	}
	
	public static void registerCraftingRecipes()
	{
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_FLOWER, FloricraftInit.SACHET_FLOWER), "amulet_sachet_flower");
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_TEMPTATION, FloricraftInit.SACHET_TEMPTATION), "amulet_sachet_temptation");
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_ANTI_ZOMBIE, FloricraftInit.SACHET_ANTI_ZOMBIE), "amulet_sachet_anti_zombie");
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_ANTI_SKELETON, FloricraftInit.SACHET_ANTI_SKELETON), "amulet_sachet_anti_skeleton");
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_ANTI_CREEPER, FloricraftInit.SACHET_ANTI_CREEPER), "amulet_sachet_anti_creeper");
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_ANTI_SPIDER, FloricraftInit.SACHET_ANTI_SPIDER), "amulet_sachet_anti_spider");
		Registerer.register(amuletRecipe(BaublesFloricraftInit.AMULET_SACHET_ANTI_ENDERMAN, FloricraftInit.SACHET_ANTI_ENDERMAN), "amulet_sachet_anti_enderman");
	}
	
	private static IRecipe amuletRecipe(Item output, Item itemIn)
	{
		return new ShapedOreRecipe(new ResourceLocation(Reference.MOD_ID, "amulet_sachet"), new ItemStack(output, 1), new Object[] {" t ", "t t", " s ", 't', FloricraftInit.HEMP_TWINE, 's', new ItemStack(itemIn, 1, OreDictionary.WILDCARD_VALUE)});
	}
}
