package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;

public class TileEntityFlowerPotRenderer extends TileEntityBasicPlanterRenderer<TileEntityFlowerPot> {
	
	@Override
	public void func_192841_a(TileEntityFlowerPot te, double x, double y, double z, float partialTicks, int destroyStage, float partial)
	{
		this.renderPlant(te, x, y, z, te.getDisplayedPlant());
		
		super.func_192841_a(te, x, y, z, partialTicks, destroyStage, partial);
	}
}
