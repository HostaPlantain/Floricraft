package com.hosta.Floricraft.block;

import com.hosta.Floricraft.tileentity.TileEntityPotPourri;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPotPourri extends BlockBasicContainer {
	
	protected static final AxisAlignedBB POTPOURRI_AABB =new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.625D, 0.75D);
	
	public BlockPotPourri(String name, Material materialIn)
	{
		super(name, materialIn);
		this.setHardness(0.3F).setResistance(1.5F).setLightOpacity(0);
	}
	
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
    	return false;
    }
    
    @Override
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.CUTOUT;
    }
    
     @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
     {
    	return POTPOURRI_AABB;
    }
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPotPourri();
	}
}
