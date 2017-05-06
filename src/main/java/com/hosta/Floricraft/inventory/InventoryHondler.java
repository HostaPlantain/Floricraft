package com.hosta.Floricraft.inventory;

import com.hosta.Floricraft.item.ItemHolder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class InventoryHondler implements IInventory {

    private ItemStack currentItem;
    private ItemStack[] items;
    private int stackLimit;
 
	public InventoryHondler(EntityPlayer player, int slotCount, int stackLimit)
	{
		this.currentItem = (player.getHeldItemMainhand() != null && !player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() instanceof ItemHolder) ? player.getHeldItemMainhand() : player.getHeldItemOffhand();
		this.items = new ItemStack[9 * slotCount];
		for (int i = 0; i < this.items.length ; i++)
		{
			this.items[i] = ItemStack.EMPTY;
		}
		this.stackLimit = stackLimit;
	}

	@Override
	public String getName()
	{
		return "InventoryHolder";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(currentItem.getDisplayName());
	}

	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return items[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.items[index] != null && !this.items[index].isEmpty())
        {
            ItemStack itemstack;
            if (this.items[index].getCount() <= count)
            {
                itemstack = this.items[index];
                this.items[index] = ItemStack.EMPTY;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.items[index].splitStack(count);
                if (this.items[index].getCount() == 0)
                {
                    this.items[index] = ItemStack.EMPTY;
                }
                this.markDirty();
                return itemstack;
            }
        }
        return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		if (this.items[index] != null && !this.items[index].isEmpty())
        {
            ItemStack itemstack = this.items[index];
            this.items[index] = ItemStack.EMPTY;
            return itemstack;
        }
        return ItemStack.EMPTY;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.items[index] = stack;
        if (stack != null && !stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
        {
        	stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return stackLimit;
	}

	@Override
	public void markDirty()
	{

	}

	@Override
	public boolean isEmpty()
	{
		return true;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player)
	{
		if(!currentItem.hasTagCompound())
        {
            currentItem.setTagCompound(new NBTTagCompound());
        }
		
        NBTTagList tags = currentItem.getTagCompound().hasKey("Items") ? (NBTTagList)currentItem.getTagCompound().getTag("Items") : new NBTTagList();
 
        for(int i = 0; i < tags.tagCount(); i++)
        {
            NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
            int slot = tagCompound.hasKey("Slot") ? tagCompound.getByte("Slot") : -999;
	        if(slot >= 0 && slot < items.length)
	        {
	        	items[slot] = new ItemStack(tagCompound);
	        }
        }
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		NBTTagList tagList = new NBTTagList();
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte)i);
                tagList.appendTag(items[i].writeToNBT(compound));
            }
        }
        
        currentItem.getTagCompound().removeTag("Items");
        currentItem.getTagCompound().setTag("Items", tagList);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return stack != null && !stack.isEmpty() && isWhiteList(stack.getItem());
	}
	
	public boolean isWhiteList(Item item)
    {
    	return true;
    }

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{

	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{

	}
}
