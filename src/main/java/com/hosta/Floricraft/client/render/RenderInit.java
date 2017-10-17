package com.hosta.Floricraft.client.render;

import com.hosta.Floricraft.client.render.entity.EntityBallonRender;
import com.hosta.Floricraft.client.render.tileentity.TileEntityDollIronSitRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityDollPlayerRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityFlowerBedRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityFlowerPotRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityPotPourriRenderer;
import com.hosta.Floricraft.client.render.tileentity.TileEntityWeatherCockRenderer;
import com.hosta.Floricraft.entity.EntityBallon;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.tileentity.TileEntityDollIronSit;
import com.hosta.Floricraft.tileentity.TileEntityDollPlayer;
import com.hosta.Floricraft.tileentity.TileEntityFlowerBed;
import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;
import com.hosta.Floricraft.tileentity.TileEntityPotPourri;
import com.hosta.Floricraft.tileentity.TileEntityWeatherCock;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInit {

	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBallon.class, new IRenderFactory<EntityBallon>()
		{
            @Override
            public Render<EntityBallon> createRenderFor(RenderManager manager)
            {
                return new EntityBallonRender(manager);
            }
        });
	}
	
	public static void registerLeaveRenders()
	{
		RenderRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS);
		RenderRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC);
		RenderRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT);
		RenderRegisterer.registerLeaves(FloricraftInit.LEAVES_CHRISTMAS_UNLIT);
		RenderRegisterer.registerGrass(FloricraftInit.FLOWER_BED);
	}
	
	public static void registerTileEntityRenders()
	{
		RenderRegisterer.registerRender(TileEntityPotPourri.class, new TileEntityPotPourriRenderer());
		RenderRegisterer.registerRender(TileEntityDollIronSit.class, new TileEntityDollIronSitRenderer());
		RenderRegisterer.registerRender(TileEntityDollPlayer.class, new TileEntityDollPlayerRenderer());
		RenderRegisterer.registerRender(TileEntityWeatherCock.class, new TileEntityWeatherCockRenderer());
		RenderRegisterer.registerRender(TileEntityFlowerPot.class, new TileEntityFlowerPotRenderer());
		RenderRegisterer.registerRender(TileEntityFlowerBed.class, new TileEntityFlowerBedRenderer());
	}
}
