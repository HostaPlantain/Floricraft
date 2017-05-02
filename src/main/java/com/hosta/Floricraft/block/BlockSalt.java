package com.hosta.Floricraft.block;

import net.minecraft.block.material.Material;

public class BlockSalt extends BlockBasicFalling {

	public BlockSalt(String name, Material materialIn)
	{
		super(name, materialIn);
		this.setHardness(0.5F).setResistance(2.5F).setHarvestLevel("shovel", 0);
	}
}
