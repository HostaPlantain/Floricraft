package com.hosta.Floricraft.proxy;

import com.hosta.Floricraft.client.helper.ClientMineHelper;
import com.hosta.Floricraft.client.render.RenderInit;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.mod.ModChecker;
import com.hosta.Floricraft.mod.baubles.BaublesFloricraftInit;
import com.hosta.Floricraft.mod.baubles.NonBaublesFloricraftInit;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerEvents()
	{
		super.registerEvents();
		MinecraftForge.EVENT_BUS.register(new com.hosta.Floricraft.client.handler.ClientEventHandler());
	}

	@Override
	public void registerRenders()
	{
		FloricraftInit.registerRenders();
		RenderInit.registerTileEntityRenders();
		RenderInit.registerEntityRenders();
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
	public void registerLeaveRenders()
	{
		RenderInit.registerLeaveRenders();
	}
	
	@Override
	public void spawnFloricParticle(World world, double[] pos, int meta, int type)
	{
		ClientMineHelper.spawnFloricParticle(world, pos, meta, type);
	}
}
