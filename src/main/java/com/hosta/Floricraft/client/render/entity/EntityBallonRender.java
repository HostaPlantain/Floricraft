package com.hosta.Floricraft.client.render.entity;

import com.hosta.Floricraft.entity.EntityBallon;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EntityBallonRender extends Render<EntityBallon> {

	public EntityBallonRender(RenderManager renderManager)
	{
		super(renderManager);
	}
	
	@Override
	public void doRender(EntityBallon entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        GlStateManager.translate(x, y, z);
        GlStateManager.scale(2.0F, 2.0F, 2.0F);

        GlStateManager.pushAttrib();
        RenderHelper.enableStandardItemLighting();
        
        Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(FloricraftInit.ITEM_BALLON, 1, entity.getColor()), ItemCameraTransforms.TransformType.FIXED);
        
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popAttrib();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBallon entity)
	{
		return null;
	}
}
