package com.hosta.Floricraft.inventory;

import com.hosta.Floricraft.item.ItemMetaSachet;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;

public class InventoryHolderSachet extends InventoryHondler {

	public InventoryHolderSachet(InventoryPlayer inventory)
	{
		super(inventory, 1, 1);
	}

	@Override
	public boolean isWhiteList(Item item)
	{
		return item instanceof ItemMetaSachet;
	}
}
