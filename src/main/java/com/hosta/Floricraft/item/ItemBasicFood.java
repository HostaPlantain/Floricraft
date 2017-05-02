package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.item.ItemFood;

public class ItemBasicFood extends ItemFood {

	public ItemBasicFood(String unlocalizedName, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		this.setUnlocalizedName(unlocalizedName).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
}
