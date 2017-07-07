package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class BlockBasicFalling extends BlockFalling {

	public BlockBasicFalling(String name, Material materialIn)
    {
        super(materialIn);
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
    }
}
