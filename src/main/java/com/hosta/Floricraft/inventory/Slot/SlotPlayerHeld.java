package com.hosta.Floricraft.inventory.Slot;

import com.hosta.Floricraft.item.ItemHolder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotPlayerHeld extends Slot {

	public SlotPlayerHeld(IInventory inventoryIn, int index, int xPosition, int yPosition)
	{
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn)
	{
		if (playerIn.getHeldItemMainhand() != null && !playerIn.getHeldItemMainhand().isEmpty() && playerIn.getHeldItemMainhand().getItem() instanceof ItemHolder)
        {
			return getSlotIndex() != playerIn.inventory.currentItem;
        }
        return getHasStack();
	}
}
