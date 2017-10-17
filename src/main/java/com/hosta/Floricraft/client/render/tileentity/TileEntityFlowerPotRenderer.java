package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;

public class TileEntityFlowerPotRenderer extends TileEntityBasicPlanterRenderer<TileEntityFlowerPot> {
	
	@Override
	public void renderTileEntityAt(TileEntityFlowerPot te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		this.renderPlant(te, x, y + 0.3, z, te.getDisplayedPlant(), 0.6F);
		
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
	}
}
