package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.tileentity.TileEntityFlowerBed;

import net.minecraft.block.state.IBlockState;

public class TileEntityFlowerBedRenderer extends TileEntityBasicPlanterRenderer<TileEntityFlowerBed> {
	
	private final double[] cos = new double[] {0.22733D, -0.32479D, 0.09746D};
	private final double[] sin = new double[] {0.24378D, 0.07498D, -0.31877D};
	
	@Override
	public void render(TileEntityFlowerBed te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		IBlockState[] states = te.getDisplayedPlant();
		
		if (states[2] != null)
		{
			int buffer = (te.getPos().getX() % 2) + (te.getPos().getZ() % 2 * 2);
			
			for (int i = 0; i < 3; i++)
			{
				double X = buffer == 0 ? cos[i] : (buffer == 1 ? sin[i] : (buffer == 2 ? -sin[i] : -cos[i]));
				double Y = buffer == 0 ? sin[i] : (buffer == 1 ? -cos[i] : (buffer == 2 ? cos[i] : -sin[i]));
				
				this.renderPlant(te, x + X, y, z + Y, states[i], 0.6F);
			}
		}
		else if (states[1] != null)
		{
			this.renderPlant(te, x - 0.25D, y, z - 0.25D, states[0], 0.8F);
			this.renderPlant(te, x + 0.25D, y, z + 0.25D, states[1], 0.8F);
		}
		else if (states[0] != null)
		{
			this.renderPlant(te, x, y, z, states[0], 1.0F);
		}
		
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
	}
}
