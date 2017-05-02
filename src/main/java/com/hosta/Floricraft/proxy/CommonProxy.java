package com.hosta.Floricraft.proxy;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	
    public void registerRenders()
	{
		
	}

	public void registerModsRenders()
	{
		
	}
	
	public void registeryModelBakeryStuff()
	{
		
	}

	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new com.hosta.Floricraft.handler.EventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new com.hosta.Floricraft.handler.TerrainGenHandler());
	}
	
	public void spawnFloricParticle(World world, double[] pos, int meta, int type)
	{

	}
}
