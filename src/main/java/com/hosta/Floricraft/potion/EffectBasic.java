package com.hosta.Floricraft.potion;

import com.hosta.Floricraft.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class EffectBasic extends Potion {
	
	public static final ResourceLocation icon = new ResourceLocation(Reference.MOD_ID,"textures/gui/container/inventory.png");
	
	public EffectBasic(boolean isBadEffectIn, int liquidColorIn)
	{
		super(isBadEffectIn, liquidColorIn);
	}
	
	@Override
	public Potion setIconIndex(int x, int y)
	{
		super.setIconIndex(x,y);
		return (Potion) this;
	}
	
	@Override
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(icon);
		
		return super.getStatusIconIndex();
	}
}
