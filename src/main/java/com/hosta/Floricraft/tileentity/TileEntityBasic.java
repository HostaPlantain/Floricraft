package com.hosta.Floricraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityBasic extends TileEntity {


	public IBlockState getBlockstate()
	{
		return this.getWorld().getBlockState(this.pos);
	}

	public Block getBlock()
	{
		return this.getWorld().getBlockState(this.pos).getBlock();
	}

	@SuppressWarnings("deprecation")
	public AxisAlignedBB getAxisAlignedBB()
	{
		return this.getBlock().getBoundingBox(getBlockstate(), worldObj, pos).offset(pos);
	}
}
