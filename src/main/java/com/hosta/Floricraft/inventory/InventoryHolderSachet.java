package com.hosta.Floricraft.inventory;

import com.hosta.Floricraft.item.ToolSachet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class InventoryHolderSachet extends InventoryHondler {

	public InventoryHolderSachet(EntityPlayer player)
	{
		super(player, 1, 1);
	}

	@Override
	public boolean isWhiteList(Item item)
	{
		return item instanceof ToolSachet;
	}
}
