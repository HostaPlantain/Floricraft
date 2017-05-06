package com.hosta.Floricraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class InventoryHolderFood extends InventoryHondler {

	public InventoryHolderFood(EntityPlayer player)
	{
		super(player, 2, 64);
	}

	@Override
	public boolean isWhiteList(Item item)
	{
		return item instanceof ItemFood;
	}
}
