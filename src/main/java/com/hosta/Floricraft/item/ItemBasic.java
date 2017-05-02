package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.item.Item;

public class ItemBasic extends Item{
	
	public ItemBasic(String name)
	{
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
}
