package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.tileentity.TileEntityDollIronSit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityDollIronSitRenderer extends TileEntitySpecialRenderer<TileEntityDollIronSit>{

	private static final double R = Math.PI / 8;

	@Override
	public void render(TileEntityDollIronSit te, double x, double y, double z, float partialTicks, int destroyStage, float partial)
	{
		renderItem(te, x, y, z);

		super.render(te, x, y, z, partialTicks, destroyStage, partial);
	}

	private void renderItem(TileEntityDollIronSit entityDoll, double x, double y, double z)
    {
		ItemStack itemstack = entityDoll.getDisplayedItem();

        if (itemstack != null && !itemstack.isEmpty())
        {
        	itemstack.setCount(1);
            
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            
            int i = entityDoll.getBlockMetadata();
            
            double radians = (double)(i + 1) * R;
            double cos;
            double sin;
            
            switch (i % 4)
            {
            	case 0:
            	default:
                    cos = Math.cos(-radians) * 0.45D;
                    sin = Math.sin(-radians) * 0.45D;
            		y -= 0.1D;
                	break;
            	case 1:
                    cos = Math.cos(-radians) * 0.5D;
                    sin = Math.sin(-radians) * 0.5D;
            		y += 0.2D;
                	break;
                case 2:
                    cos = Math.cos(-radians-R) * 0.5D;
                    sin = Math.sin(-radians-R) * 0.5D;
            		y += 0.2D;
                	break;
                case 3:
                    cos = Math.cos(-radians-R) * 0.5D;
                    sin = Math.sin(-radians-R) * 0.5D;
            		y -= 0.1D;
                	break;
            }
            
            GlStateManager.translate(x + 0.5D + sin, y + 0.5D, z + 0.5D + cos);
            GlStateManager.rotate(180.0F - (float)i * 22.5F, 0.0F, 1.0F, 0.0F);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);

            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItem(itemstack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
