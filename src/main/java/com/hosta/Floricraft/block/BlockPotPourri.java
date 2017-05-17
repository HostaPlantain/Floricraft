package com.hosta.Floricraft.block;

import javax.annotation.Nullable;

import com.hosta.Floricraft.tileentity.TileEntityPotPourri;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate)
	{
		TileEntityPotPourri potpourri = getTileEntityPotPourri(world, pos);
		if(potpourri != null)
    	{
			for(int i = 0; i < potpourri.getSizeInventory(); i++)
			{
	    		if (potpourri.getStackInSlot(i) != null && !potpourri.getStackInSlot(i).isEmpty())
	    		{
	    			world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), potpourri.getStackInSlot(i)));
	    		}
			}
    	}
	    super.breakBlock(world, pos, blockstate);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		TileEntityPotPourri potpourri = getTileEntityPotPourri(worldIn, pos);
    	if(potpourri != null)
        {
    		potpourri.onClick(playerIn, playerIn.getHeldItem(hand), hand);
            return true;
        }
        return false;
    }
    
	@Nullable
    public TileEntityPotPourri getTileEntityPotPourri(IBlockAccess world, BlockPos pos)
    {
    	TileEntity te = world.getTileEntity(pos);
    	if(te != null && te instanceof TileEntityPotPourri)
    	{
    		TileEntityPotPourri potPourri = (TileEntityPotPourri)te;
    		return potPourri;
    	}
		return null;
    }
	
    @Override
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.TRANSLUCENT;
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
