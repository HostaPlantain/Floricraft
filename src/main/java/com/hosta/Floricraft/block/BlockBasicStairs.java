package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockBasicStairs extends BlockStairs {

	public BlockBasicStairs(String name, IBlockState modelState)
	{
		super(modelState);
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
}
