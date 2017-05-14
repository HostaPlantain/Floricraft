package com.hosta.Floricraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class TileEntityInventoryWithRender extends TileEntityBasicWithRender implements IInventory {

    protected ItemStack[] items;
    private final int STACKLIMIT;

    public TileEntityInventoryWithRender(int inventorySize, int stackLimit)
    {
    	super();
    	this.items = new ItemStack[inventorySize];
    	this.STACKLIMIT = stackLimit;
    }
    
	public void onClick(EntityPlayer player, ItemStack stackIn, EnumHand hand)
    {
		if (stackIn != null && isWhiteList(stackIn.getItem()))
        {
			int maxIndex = 0;
			
            for (int index = items.length - 1; index >= 0; index--)
            {
            	if (items[index] != null)
            	{
            		maxIndex = index;
            		break;
            	}
            }
			
            for (int index = maxIndex; index < items.length; index++)
            {
	            if (this.items[index] == null)
	            {
	            	this.items[index] = stackIn.splitStack(this.getInventoryStackLimit());
	            }
	            else if (this.items[index].getItem() == stackIn.getItem() && this.items[index].stackSize < this.getInventoryStackLimit())
	            {
	            	ItemStack itemStack1 = this.items[index].copy();
	            	itemStack1.stackSize = 1;
	            	ItemStack itemStack2 = stackIn.copy();
	            	itemStack2.stackSize = 1;
	            	
	            	if (itemStack1.isItemEqual(itemStack2))
	            	{
	            		int amount = this.items[index].stackSize;
		            	this.items[index].stackSize = Math.min(amount + stackIn.stackSize, this.getInventoryStackLimit());
	            		stackIn.splitStack(this.items[index].stackSize - amount);
	            	}
	            }
	            
	            if (stackIn.stackSize <= 0)
	            {
	            	player.setHeldItem(hand, null);
	            	break;
	            }
            }
        }
		else
		{
			for (int index = getSizeInventory() - 1; index >= 0; index--)
            {
				if (this.items[index] != null)
				{
	        		ItemHandlerHelper.giveItemToPlayer(player, this.items[index]);
					this.items[index] = null;
					break;
				}
            }
		}
        this.markDirty();
    }

	protected void setMoveOverItems(int index)
	{
		if (this.items[index] == null)
		{
	        for (int i = index; i < items.length - 1; i++)
	        {
	        	this.items[i] = this.items[i + 1];
	        }
	    	this.items[items.length - 1] = null;
		}
	}
	
	private void sendPacket()
	{
		if (!this.getWorld().isRemote)
		{
			for (EntityPlayer player : this.getWorld().playerEntities)
	        {
				((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
	        }
		}
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		this.sendPacket();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		NBTTagList tags = compound.hasKey("Items") ? (NBTTagList) compound.getTag("Items") : new NBTTagList();
		ItemStack[] items = new ItemStack[this.items.length];
        for(int i = 0; i < tags.tagCount(); i++)
        {
            NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
            int slot = tagCompound.hasKey("Slot") ? tagCompound.getByte("Slot") : -999;
	        if(slot >= 0 && slot < items.length)
	        {
	        	items[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
	        }
        }
        this.items = items;
        
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagList tagList = new NBTTagList();
		
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte)i);
                tagList.appendTag(items[i].writeToNBT(tagCompound));
            }
        }
        
        compound.removeTag("Items");
        compound.setTag("Items", tagList);
        
		return super.writeToNBT(compound);
	}
	
	@Override
	public String getName()
	{
		return "InventoryPotpourri";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
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
		ItemStack itemstack = ItemStackHelper.getAndSplit(this.items, index, count);

        if (itemstack != null)
        {
            this.setMoveOverItems(index);
            this.markDirty();
        }

        return itemstack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		if (this.items[index] != null)
        {
            ItemStack itemstack = this.items[index];
            this.items[index] = null;
            setMoveOverItems(index);
            this.markDirty();
            return itemstack;
        }
        return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
        	stack.stackSize = this.getInventoryStackLimit();
        }
		
		if (this.items[index] != null && stack != null)
		{
			ItemStack itemStack1 = this.items[index].copy();
	        itemStack1.stackSize = 1;
	        ItemStack itemStack2 = stack.copy();
	        itemStack2.stackSize = 1;
	        
	        if (itemStack1.isItemEqual(itemStack2) && this.items[index].stackSize + stack.stackSize < this.getInventoryStackLimit())
	       	{
	           	this.items[index].stackSize += stack.stackSize;
	        }
	        else if (this.items[items.length - 1] == null)
			{
				for (int i = items.length - 1; i > index; i--)
				{
					this.items[i] = this.items[i - 1];
				}
				this.items[index] = stack;
			}
		}
		else
		{
			this.items[index] = stack;
		}
        
        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return STACKLIMIT;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player)	{	}

	@Override
	public void closeInventory(EntityPlayer player)	{	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return stack != null && isWhiteList(stack.getItem());
	}

	protected abstract boolean isWhiteList(Item item);

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)	{	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()	{	}
}
