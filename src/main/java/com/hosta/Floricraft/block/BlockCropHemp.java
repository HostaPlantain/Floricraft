package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCropHemp extends BlockBasicCrops {
	
	public BlockCropHemp(String name)
	{
		super(name);
	}

	@Override
	protected Item getSeed()
	{
		return FloricraftInit.SEED_HEMP;
	}
	
	@Override
	protected Item getCrop()
	{
		return FloricraftInit.HEMP_YARN;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
		if(isMaxAge(state))
		{
			drops.add(getIncreacedDrop(world, fortune));
		}
		
		super.getDrops(drops, world, pos, state, fortune);
    }
}
