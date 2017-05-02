package com.hosta.Floricraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiBasic extends Gui {

	protected Minecraft mc = Minecraft.getMinecraft();
	protected int[] scale = new int[] {512, 512};

	public void setScaled()
	{
		ScaledResolution scaled = new ScaledResolution(mc);
		scale[0] = scaled.getScaledWidth();
		scale[1] = scaled.getScaledHeight();
	}
}
