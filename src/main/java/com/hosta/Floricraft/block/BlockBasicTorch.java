package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.BlockTorch;

public class BlockBasicTorch extends BlockTorch {

	public BlockBasicTorch(String name)
	{
		super();
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
		this.setLightLevel(1.0f);
	}
}
