package com.hosta.Floricraft.proxy;

import com.hosta.Floricraft.client.helper.ClientMineHelper;
import com.hosta.Floricraft.client.render.color.ColorRegisterer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityDollIronSitRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityFlowerPotRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityWeatherCockRenderer;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.mod.ModChecker;
import com.hosta.Floricraft.mod.baubles.BaublesFloricraftInit;
import com.hosta.Floricraft.mod.baubles.NonBaublesFloricraftInit;
import com.hosta.Floricraft.tileentity.TileEntityDollIronSit;
import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;
import com.hosta.Floricraft.tileentity.TileEntityWeatherCock;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenders()
	{
		FloricraftInit.registerRenders();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDollIronSit.class, new TileEntityDollIronSitRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWeatherCock.class, new TileEntityWeatherCockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlowerPot.class, new TileEntityFlowerPotRenderer());
		ColorRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS);
		ColorRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC);
		ColorRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT);
		ColorRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS_UNLIT);
	}
	
	@Override
	public void registerModsRenders()
	{
		if (ModChecker.isBaublesLoaded)
		{
			BaublesFloricraftInit.registerRenders();
		}
		else
		{
			NonBaublesFloricraftInit.registerRenders();
		}
	}

	@Override
	public void registeryModelBakeryStuff()
	{
		FloricraftInit.registeryModelBakeryStuffs();
	}

	@Override
	public void registerEvents()
	{
		super.registerEvents();
		MinecraftForge.EVENT_BUS.register(new com.hosta.Floricraft.client.handler.ClientEventHandler());
	}
	
	@Override
	public void spawnFloricParticle(World world, double[] pos, int meta, int type)
	{
		ClientMineHelper.spawnFloricParticle(world, pos, meta, type);
	}
}
