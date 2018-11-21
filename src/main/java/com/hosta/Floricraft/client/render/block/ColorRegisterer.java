package com.hosta.Floricraft.client.render.block;

import javax.annotation.Nullable;

import com.hosta.Floricraft.block.BlockLeavesChristmas;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorRegisterer {

	public static void registerLeaves(Block block)
	{
		if (block instanceof BlockLeaves)
		{
			BlockLeaves leaves = (BlockLeaves)block;
			
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler
			(new IBlockColor()
			{
				public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
				{
					return leaves instanceof BlockLeavesChristmas ? ColorizerFoliage.getFoliageColorPine() : (worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic());
				}
			},
			new Block[] {leaves});
			
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler
			(new IItemColor()
			{
				@Override
				public int colorMultiplier(ItemStack stack, int tintIndex)
				{
					@SuppressWarnings("deprecation")
					IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
					return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
				}
			},
			new Block[] {leaves});
				
			setGraphicsLevel(leaves);
		}
	}

	public static void registerGrass(Block block)
	{
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler
		(new IBlockColor()
	    {
	    	public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
	    	{
	    		return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
	    	}
	    }, 
	    new Block[] {block});
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler
		(new IItemColor()
		{
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex)
			{
				@SuppressWarnings("deprecation")
				IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
				return Minecraft.getMinecraft().getBlockColors().colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
			}
		},
		new Block[] {block});
	}
	
	public static void setGraphicsLevel(Block leaves)
	{
		if (leaves instanceof BlockLeaves)
		{
			((BlockLeaves)leaves).setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
		}
	}
}
