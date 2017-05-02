package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.item.Item;

public class ToolBasic extends Item {
	
	public ToolBasic(String name)
	{
		super();
		this.setUnlocalizedName(name).setMaxStackSize(1).setCreativeTab(FloricraftTabs.tabFloricraft);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}
