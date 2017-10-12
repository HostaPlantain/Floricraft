package com.hosta.Floricraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileEntityFlowerBed extends TileEntityPlanter {

	private ItemStack[] inventory = new ItemStack[3];

	public void onClick(EntityPlayer player, ItemStack stackIn, EnumHand hand)
    {
        if(this.inventory[2] == null && stackIn != null)
        {
            Item item = stackIn.getItem();
            if (item instanceof ItemBlock)
           	{
           		Block block = ((ItemBlock)item).getBlock();
           		if (block instanceof BlockLilyPad || block instanceof BlockVine)
           		{
           			
           		}
           		else if (block instanceof IPlantable || block instanceof IShearable)
           		{
           			this.setDisplayedItem(stackIn.splitStack(1));
           			if (!this.worldObj.isRemote)
                	{
                		player.setHeldItem(hand, stackIn.stackSize == 0 ? null : stackIn);
                	}
            		this.markDirty();
            	}
            }
        }
        else
        {
        	for (int i = 2; i >= 0; i--)
        	{
        		 if (this.inventory[i] != null && stackIn == null)
        		 {
        			if (!this.worldObj.isRemote)
        	        {
        	        	ItemHandlerHelper.giveItemToPlayer(player, this.inventory[i]);
        	        }
        	        this.setDisplayedItem(stackIn, i);
        	    	this.markDirty();
        	    	break;
        		 }
        	}
        }
    }

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);

		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < 3; i++)
		{
			if (inventory[i] != null)
			{
				NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte)i);
                tagList.appendTag(inventory[i].writeToNBT(tagCompound));
			}
		}

		nbt.removeTag("Items");
		nbt.setTag("Items", tagList);
        
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList tags = nbt.hasKey("Items") ? (NBTTagList) nbt.getTag("Items") : new NBTTagList();
        for(int i = 0; i < tags.tagCount(); i++)
        {
            NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
            int slot = tagCompound.hasKey("Slot") ? tagCompound.getByte("Slot") : -999;
	        if(slot >= 0 && slot < 3)
	        {
	        	this.setDisplayedItem(ItemStack.loadItemStackFromNBT(tagCompound), i);
	        }
        }
	}

	public ItemStack[] getDisplayedItems()
	{
		return inventory;
	}

	public Block[] getDisplayedBlock()
	{
		Block[] blocks = new Block[3];
		for (int i = 0; i < 3; i++)
		{
			if (inventory[i] != null)
			{
				Item item = inventory[i].getItem();
				if (item instanceof ItemBlock)
				{
					blocks[i] = ((ItemBlock) item).getBlock();
				}
			}
		}
		return blocks;
	}
	
    @SuppressWarnings("deprecation")
	public IBlockState[] getDisplayedPlant()
	{
    	Block[] blocks = getDisplayedBlock();
    	IBlockState[] blockStates = new IBlockState[3];
    	
		for (int i = 0; i < 3; i++)
		{
			if (inventory[i] != null)
			{
				ItemBlock item = (ItemBlock)inventory[i].getItem();
				
				int meta = item.getMetadata(inventory[i].getItemDamage());
				blockStates[i] = blocks[i].getStateFromMeta(meta);
			}
		}
		
		return blockStates;
	}
    
    private void setDisplayedItem(ItemStack stack)
	{
		for (int i = 0; i < 3; i++)
		{
			if (inventory[i] == null)
			{
				setDisplayedItem(stack, i);
				break;
			}
		}
	}
    
    private void setDisplayedItem(ItemStack stack, int i)
	{
		inventory[i] = stack;
	}
}
