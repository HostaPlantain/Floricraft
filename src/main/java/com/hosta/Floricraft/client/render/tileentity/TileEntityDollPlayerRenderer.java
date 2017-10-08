package com.hosta.Floricraft.client.render.tileentity;

import java.util.Map;
import java.util.UUID;

import com.hosta.Floricraft.tileentity.TileEntityDollPlayer;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TileEntityDollPlayerRenderer extends TileEntitySpecialRenderer<TileEntityDollPlayer>{
	
	private static final double R = Math.PI / 8;
    private final ModelBase MODEL_STEVE = new ModelPlayer(0.0F, false);
    private final ModelBase MODEL_ALEX = new ModelPlayer(0.0F, true);
    private final ResourceLocation SKIN_STEVE = new ResourceLocation("textures/entity/steve.png");

	@Override
	public void render(TileEntityDollPlayer te, double x, double y, double z, float partialTicks, int destroyStage, float p_192841_10_)
	{
		renderDoll(te, x, y, z);
		renderItem(te, x, y, z);

		super.render(te, x, y, z, partialTicks, destroyStage, p_192841_10_);
	}

	private void renderDoll(TileEntityDollPlayer te, double x, double y, double z)
	{
		GameProfile profile = te.getDisplayedplayer();
		Minecraft minecraft = Minecraft.getMinecraft();
		
		ResourceLocation skin = null;
		boolean isSteve = true;
		
		if (profile != null)
		{
	        Map<Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(profile);
            UUID uuid = EntityPlayer.getUUID(profile);
        
	        if (map.containsKey(Type.SKIN))
            {
	        	skin = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
            }
            else
            {
                skin = DefaultPlayerSkin.getDefaultSkin(uuid);
            }
	        
	        NetworkPlayerInfo info = Minecraft.getMinecraft().getConnection().getPlayerInfo(uuid);
	        if (info != null)
	        {
		        isSteve = "default".equals(info.getSkinType());
	        }
		}
		else
		{
			skin = SKIN_STEVE;
		}
		
        this.bindTexture(skin);
		
		GlStateManager.pushMatrix();
        GlStateManager.disableLighting();

        int i = te.getBlockMetadata();
        
        GlStateManager.translate(x + 0.5D, y + 1.1D, z + 0.5D);
        GlStateManager.rotate(180.0F - (float)i * 22.5F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        GlStateManager.pushAttrib();
        RenderHelper.enableStandardItemLighting();
        
        if (isSteve)
        {
            MODEL_STEVE.render(Minecraft.getMinecraft().player, 0.4F, 0.75F, 0.0F, 0.0F, 0.0F, 0.05F);
        }
        else
        {
            MODEL_ALEX.render(Minecraft.getMinecraft().player, 0.4F, 0.75F, 0.0F, 0.0F, 0.0F, 0.05F);
        }
        
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popAttrib();

        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
	}

	private void renderItem(TileEntityDollPlayer entityDoll, double x, double y, double z)
    {
		ItemStack itemstack = entityDoll.getDisplayedItem();

        if (itemstack != null)
        {
        	itemstack.setCount(1);
            
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            
            int i = entityDoll.getBlockMetadata();
            
            double radians = (double)(i + 1) * R + R / 2;
            double cos = Math.cos(-radians) * 0.25D;
            double sin = Math.sin(-radians) * 0.25D;
              
            GlStateManager.translate(x + 0.5D + sin, y + 0.35D, z + 0.5D + cos);
            GlStateManager.rotate(180.0F - (float)i * 22.5F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-45.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.3F, 0.3F, 0.3F);

            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            
            Minecraft.getMinecraft().getRenderItem().renderItem(itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
