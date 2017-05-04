package com.hosta.Floricraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;

public class InventoryHolderFlower extends InventoryHondler {

	public InventoryHolderFlower(EntityPlayer player)
	{
		super(player, 2, 64);
	}

	@Override
	public boolean isWhiteList(Item item)
	{
		return item instanceof ItemBlock && (((ItemBlock)item).getBlock() instanceof IPlantable || ((ItemBlock)item).getBlock() instanceof IShearable);
	}
}
