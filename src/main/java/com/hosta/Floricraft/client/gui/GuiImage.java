package com.hosta.Floricraft.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiImage extends GuiBasic{

	private ResourceLocation resourceLocation;
	private int tick;

	public GuiImage(String text, int tick)
	{
		this(new ResourceLocation("floricraft" + ":" + "textures/gui/overlay/" + text + ".png"), tick);
	}
	
	public GuiImage(ResourceLocation resourceLocation, int tick)
	{
		setScaled();
		setResourceLocation(resourceLocation);
		this.tick = tick;
	}
	
	private void setResourceLocation(ResourceLocation resourceLocation)
	{
		this.resourceLocation = resourceLocation;
	}

	public void renderCenteredImage()
	{
		if (tick > 0)
		{
			mc.getTextureManager().bindTexture(resourceLocation);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(scale[0] / 2 - 128, scale[1] / 2 - 128, 0, 0, 256, 256);
			--tick;
		}
	}
	
	public boolean isUnNeeded()
	{
		return tick <= 0;
	}
}
