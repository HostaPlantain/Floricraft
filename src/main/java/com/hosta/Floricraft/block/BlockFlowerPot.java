package com.hosta.Floricraft.block;

import javax.annotation.Nullable;

import com.hosta.Floricraft.tileentity.TileEntityFlowerPot;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFlowerPot extends BlockBasicContainer {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
	
	public BlockFlowerPot(String name)
	{
		super(name, Material.CIRCUITS);
		this.setHardness(0.0F).setResistance(0.0F).setLightOpacity(0);
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
		TileEntityFlowerPot planter = getTileEntityPlanter(world, pos);
		if(planter != null)
    	{
    		if (planter.getDisplayedItem() != null)
    		{
    			world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), planter.getDisplayedItem()));
    		}
    	}
	    super.breakBlock(world, pos, blockstate);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItemIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	TileEntityFlowerPot planter = getTileEntityPlanter(worldIn, pos);
    	if(planter != null)
        {
    		planter.onClick(playerIn, playerIn.getHeldItem(hand), hand);
            return true;
        }
        return false;
    }
    
	@Nullable
    public TileEntityFlowerPot getTileEntityPlanter(IBlockAccess world, BlockPos pos)
    {
    	TileEntity te = world.getTileEntity(pos);
    	if(te != null && te instanceof TileEntityFlowerPot)
    	{
    		TileEntityFlowerPot planter = (TileEntityFlowerPot)te;
    		return planter;
    	}
		return null;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFlowerPot();
	}
}
