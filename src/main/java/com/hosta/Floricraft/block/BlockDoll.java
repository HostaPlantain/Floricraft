package com.hosta.Floricraft.block;

import javax.annotation.Nullable;

import com.hosta.Floricraft.tileentity.TileEntityDoll;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockDoll extends BlockBasicRotation{

	public BlockDoll(String name)
	{
		super(name, Material.CLOTH);
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
		TileEntityDoll doll = getTileEntityDoll(world, pos);
		if(doll != null)
    	{
    		if (doll.getDisplayedItem() != null)
    		{
    			world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), doll.getDisplayedItem()));
    		}
    	}
	    super.breakBlock(world, pos, blockstate);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItemIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	TileEntityDoll doll = getTileEntityDoll(worldIn, pos);
    	if(doll != null)
        {
        	doll.onClick(playerIn, playerIn.getHeldItem(hand), hand);
            return true;
        }
    	return false;
    }
    
	@Nullable
    public TileEntityDoll getTileEntityDoll(IBlockAccess world, BlockPos pos)
    {
    	TileEntity te = world.getTileEntity(pos);
    	if (te != null && te instanceof TileEntityDoll)
    	{
    		TileEntityDoll doll = (TileEntityDoll)te;
    		return doll;
    	}
		return null;
    }
}
