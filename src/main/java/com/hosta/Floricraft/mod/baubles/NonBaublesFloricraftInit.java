package com.hosta.Floricraft.mod.baubles;

import com.hosta.Floricraft.init.Registerer;

import net.minecraft.item.Item;

public class NonBaublesFloricraftInit {

	//Amulet Sachet
	public static final Item AMULET_SACHET_FLOWER = new NonBaublesItemMetaAmuletSachet("amulet_sachet_flower"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_TEMPTATION = new NonBaublesItemMetaAmuletSachet("amulet_sachet_temptation"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_ZOMBIE = new NonBaublesItemMetaAmuletSachet("amulet_sachet_anti_zombie"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_SKELETON = new NonBaublesItemMetaAmuletSachet("amulet_sachet_anti_skeleton"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_CREEPER = new NonBaublesItemMetaAmuletSachet("amulet_sachet_anti_creeper"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_SPIDER = new NonBaublesItemMetaAmuletSachet("amulet_sachet_anti_spider"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	public static final Item AMULET_SACHET_ANTI_ENDERMAN = new NonBaublesItemMetaAmuletSachet("amulet_sachet_anti_enderman"/*, new ItemStack(FloricraftInit.essential_oil, 1, 0)*/);
	
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
}
