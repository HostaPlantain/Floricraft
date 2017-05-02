package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

public class ItemToolPurner extends ItemShears{
	
	public ItemToolPurner(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return true;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		int i = itemStack.getMetadata();
		return new ItemStack(FloricraftInit.PURNER, 1, ++i);
	}
}
