package com.hosta.Floricraft;

import com.hosta.Floricraft.config.ConfigChecker;
import com.hosta.Floricraft.handler.RecipeHandler;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.mod.ModChecker;
import com.hosta.Floricraft.mod.baubles.BaublesFloricraftInit;
import com.hosta.Floricraft.mod.baubles.NonBaublesFloricraftInit;
import com.hosta.Floricraft.proxy.CommonProxy;
import com.hosta.Floricraft.world.gen.ore.WorldGenOreOverWorld;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Floricraft {

	@Mod.Instance(Reference.MOD_ID)
    public static Floricraft instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigChecker.lordConfig(event.getSuggestedConfigurationFile());
		
		FloricraftInit.init();
		FloricraftInit.registers();
		FloricraftInit.registeryOres();
		
		if (ModChecker.isBaublesLoaded)
		{
			BaublesFloricraftInit.init();
			BaublesFloricraftInit.registers();
		}
		else
		{
			NonBaublesFloricraftInit.init();
			NonBaublesFloricraftInit.registers();
		}

	    if (ConfigChecker.getGenSalt())
	    {
	    	GameRegistry.registerWorldGenerator(new WorldGenOreOverWorld(), 0);
	    }
		
		proxy.registerEvents();
		
		RecipeHandler.registerCraftingRecipes();

		if (ModChecker.isBaublesLoaded)
		{
			BaublesFloricraftInit.registerCraftingRecipes();
		}
		
		proxy.registerRenders();
		proxy.registerModsRenders();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//RecipeHandler.registerFurnaceRecipes();
		RecipeHandler.registerBrewingRecipes();
		
		proxy.registerLeaveRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
