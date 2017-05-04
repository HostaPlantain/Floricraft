package com.hosta.Floricraft.inventory;

import com.hosta.Floricraft.inventory.Slot.SlotHeldSlot;
import com.hosta.Floricraft.inventory.Slot.SlotHolder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHolder extends Container {
	
    private InventoryHondler inventory;
	
	public ContainerHolder(EntityPlayer player, int guiID)
	{
		if (guiID == 1)
		{
			inventory = new InventoryHolderSachet(player);
		}
		
		int slotCount = getSlotCount();
        inventory.openInventory(player);
 
        for (int k = 0; k < slotCount; ++k)
        {
	        for (int j = 0; j < 9; ++j)
	        {
	        	this.addSlotToContainer(new SlotHolder(inventory, j, j * 18 + 8, slotCount * 18 + 2));
	        }
		}
        for (int k = 0; k < 3; ++k)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + k * 9 + 9, j * 18 + 8, k * 18 + slotCount * 18 + 33));
            }
        }
        for (int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new SlotHeldSlot(player.inventory, j, j * 18 + 8, slotCount * 18 + 91));
        }
	}

	public int getSlotCount()
	{
		return inventory.getSizeInventory() / 9;
	}
	
	@Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
 
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
    	ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
 
            if (index < this.inventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                	return null;
                }
            }
            else if (isBlckList(itemstack1) || !this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false))
            {
                return null;
            }
            
            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        
        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        this.inventory.closeInventory(player);
    }
    
    public boolean isBlckList(ItemStack itemStack)
    {
		return itemStack != null && (!inventory.isWhiteList(itemStack.getItem()) || itemStack.stackSize > inventory.getInventoryStackLimit());
    }
}
