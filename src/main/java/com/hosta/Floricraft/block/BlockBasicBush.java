package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBasicBush extends BlockBush {

	public BlockBasicBush(String name)
	{
		super(Material.PLANTS);
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
		this.setSoundType(SoundType.PLANT);
	}
}
