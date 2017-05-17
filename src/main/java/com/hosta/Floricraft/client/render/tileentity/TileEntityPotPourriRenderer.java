package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;
import com.hosta.Floricraft.tileentity.TileEntityPotPourri;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class TileEntityPotPourriRenderer extends TileEntitySpecialRenderer<TileEntityPotPourri> {

	@Override
	public void renderTileEntityAt(TileEntityPotPourri te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		for (int i = 0; i < te.getSizeInventory(); i++)
		{
			if (te.getStackInSlot(i) != null && !te.getStackInSlot(i).isEmpty())
			{
				renderPetals(te, x, 0.0625F * i + y, z, ItemMetaFlower.getColorIDFromMeta(te.getStackInSlot(i).getItemDamage()), te.getStackInSlot(i).getItem() == FloricraftInit.PETALS_SALTY ? 1 : 0);
			}
		}
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
	}
	
	private void renderPetals(TileEntityPotPourri te, double x, double y, double z, int meta, int isSalty)
	{
		GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate(x, y, z);
        
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldRenderer = tessellator.getBuffer();
        
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/blocks/potpourri_petals.png"));
        bindCube(worldRenderer, 0.3125F, 0.6875F, 0.0625F, 0.125F, 0.3125F, 0.6875F, isSalty * 0.5F, isSalty * 0.5F + 0.375F, meta * 0.0625F, meta * 0.0625F + 0.0625F);
        tessellator.draw();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
	}
	
	private void bindCube(VertexBuffer worldRenderer, double minX, double maxX, double minY, double maxY, double minZ, double maxZ, double minU, double maxU, double minV, double maxV)
	{
        worldRenderer.pos(minX, maxY, minZ).tex(minU, maxV).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).tex(maxU, maxV).endVertex();
        worldRenderer.pos(maxX, minY, minZ).tex(maxU, minV).endVertex();
        worldRenderer.pos(minX, minY, minZ).tex(minU, minV).endVertex();

        worldRenderer.pos(maxX, maxY, minZ).tex(minU, maxV).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).tex(maxU, maxV).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).tex(maxU, minV).endVertex();
        worldRenderer.pos(maxX, minY, minZ).tex(minU, minV).endVertex();

        worldRenderer.pos(maxX, maxY, maxZ).tex(minU, maxV).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).tex(maxU, maxV).endVertex();
        worldRenderer.pos(minX, minY, maxZ).tex(maxU, minV).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).tex(minU, minV).endVertex();

        worldRenderer.pos(minX, maxY, maxZ).tex(minU, maxV).endVertex();
        worldRenderer.pos(minX, maxY, minZ).tex(maxU, maxV).endVertex();
        worldRenderer.pos(minX, minY, minZ).tex(maxU, minV).endVertex();
        worldRenderer.pos(minX, minY, maxZ).tex(minU, minV).endVertex();

        worldRenderer.pos(minX, maxY, maxZ).tex(minU, maxV).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).tex(maxU, maxV).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).tex(maxU, minV).endVertex();
        worldRenderer.pos(minX, maxY, minZ).tex(minU, minV).endVertex();

        worldRenderer.pos(minX, minY, minZ).tex(minU, maxV).endVertex();
        worldRenderer.pos(maxX, minY, minZ).tex(maxU, maxV).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).tex(maxU, minV).endVertex();
        worldRenderer.pos(minX, minY, maxZ).tex(minU, minV).endVertex();
	}
}
