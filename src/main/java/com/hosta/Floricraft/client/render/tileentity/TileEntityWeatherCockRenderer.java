package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.helper.WindHelper;
import com.hosta.Floricraft.tileentity.TileEntityWeatherCock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityWeatherCockRenderer extends TileEntitySpecialRenderer<TileEntityWeatherCock> {

	@Override
	public void renderTileEntityAt(TileEntityWeatherCock te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		ItemStack itemstack = new ItemStack(te.getBlock(), 1, te.getBlockMetadata());
        
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        
        float wind = (float) (WindHelper.getAngle(getWorld()) / Math.PI * 180);
        
        GlStateManager.translate(x + 0.5D , y + 0.5D, z + 0.5D);
        GlStateManager.rotate(90.0F + wind, 0.0F, -1.0F, 0.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);

        GlStateManager.pushAttrib();
        RenderHelper.enableStandardItemLighting();
        
        Minecraft.getMinecraft().getRenderItem().renderItem(itemstack, ItemCameraTransforms.TransformType.FIXED);
        
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popAttrib();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
	}
}
