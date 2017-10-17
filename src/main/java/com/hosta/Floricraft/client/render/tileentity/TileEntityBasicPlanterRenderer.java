package com.hosta.Floricraft.client.render.tileentity;

import com.hosta.Floricraft.tileentity.TileEntityBasicWithRender;
import com.hosta.Floricraft.tileentity.TileEntityPlanter;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class TileEntityBasicPlanterRenderer<T extends TileEntityBasicWithRender> extends TileEntitySpecialRenderer<T> {
	
	public void renderPlant(TileEntityPlanter planter, double x, double y, double z, IBlockState iblockstate, float scale)
	{
		if (iblockstate != null && iblockstate.getRenderType() == EnumBlockRenderType.MODEL)
        {
			World world = planter.getWorld();
			renderPlant(iblockstate,world, planter, x, y, z, scale);
			
			if(iblockstate.getBlock() instanceof BlockDoublePlant)
			{
				iblockstate = iblockstate.withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER);
				
				renderPlant(iblockstate,world, planter, x, y + scale, z, scale);
			}
        }
	}

	private void renderPlant(IBlockState iblockstate,World world, TileEntityPlanter planter, double x, double y, double z, float scale)
	{
		if (iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE)
		{
			this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder vertexbuffer = tessellator.getBuffer();

			BlockPos blockpos = planter.getPos();
			float scaleR = (1.0F - scale) / 2;
			
			vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
			GlStateManager.translate(x - ((double)blockpos.getX() * scale) + scaleR, y - ((double)blockpos.getY() * scale), z - ((double)blockpos.getZ() * scale) + scaleR);
            GlStateManager.scale(scale, scale, scale);
            
            Block block = iblockstate.getBlock();
            Block.EnumOffsetType block$enumoffsettype = block.getOffsetType();
            	
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			IBakedModel model = blockrendererdispatcher.getModelForState(iblockstate);
			
            if (block$enumoffsettype != Block.EnumOffsetType.NONE)
            {
                @SuppressWarnings("deprecation")
				boolean flag = Minecraft.isAmbientOcclusionEnabled() && iblockstate.getLightValue() == 0 && model.isAmbientOcclusion();
            	
            	long k = 0;
            	
                if (flag)
                {
                    k = MathHelper.getPositionRandom(blockpos);
                }
                else
                {
                	int i = blockpos.getX();
                    int j = blockpos.getZ();
                    k = (long)(i * 3129871) ^ (long)j * 116129781L;
                    k = k * k * 42317861L + k * 11L;
                }
            	
                double d0 = ((double)((float)(k >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
                double d1 = 0.0D;
                double d2 = ((double)((float)(k >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;

                if (block$enumoffsettype == Block.EnumOffsetType.XYZ)
                {
                    d1 = ((double)((float)(k >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
                }
                
                GlStateManager.translate(-d0, -d1, -d2);
            }
            
			blockrendererdispatcher.getBlockModelRenderer().renderModel(world, model, iblockstate, blockpos, vertexbuffer, false, MathHelper.getPositionRandom(blockpos));
			tessellator.draw();
			
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
        }
	}
}
