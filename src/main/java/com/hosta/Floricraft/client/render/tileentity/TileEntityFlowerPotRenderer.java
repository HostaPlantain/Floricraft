package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;

import net.minecraft.client.renderer.BufferBuilder;

public class TileEntityFlowerPotRenderer extends TileEntityBasicPlanterRenderer<TileEntityFlowerPot> {
	
	@Override
	public void renderTileEntityFast(TileEntityFlowerPot te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer)
	{
		this.renderPlant(te, x, y, z, te.getDisplayedPlant());
		
		super.renderTileEntityFast(te, x, y, z, partialTicks, destroyStage, partial, buffer);
	}
}
