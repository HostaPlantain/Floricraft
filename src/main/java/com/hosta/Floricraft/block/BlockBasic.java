package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block{

	public BlockBasic(String name, Material materialIn)
	{
		super(materialIn);
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
}
