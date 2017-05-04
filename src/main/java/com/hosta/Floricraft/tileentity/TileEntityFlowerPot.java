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
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileEntityFlowerPot extends TileEntityPlanter {

	private ItemStack inventory;

	public void onClick(EntityPlayer player, ItemStack stackIn, EnumHand hand)
    {
        if(this.getDisplayedItem() == null && stackIn != null)
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
            	}
            }
        }
        else if (this.getDisplayedItem() != null && stackIn == null)
        {
        	if (!this.worldObj.isRemote)
        	{
        		ItemHandlerHelper.giveItemToPlayer(player, this.inventory);
        	}
            this.setDisplayedItem(stackIn);
        }
    }

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);

    	NBTTagCompound stackTag = new NBTTagCompound();
	    if (this.getDisplayedItem() != null)
	    {
	    	stackTag = this.getDisplayedItem().writeToNBT(stackTag);
	    }
	    nbt.setTag("Item", stackTag);
	    
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);

	    NBTTagCompound stackTag = nbt.getCompoundTag("Item");
	    this.setDisplayedItem(ItemStack.loadItemStackFromNBT(stackTag));
	}

	public ItemStack getDisplayedItem()
	{
		return inventory;
	}

	public Block getDisplayedBlock()
	{
		if (inventory != null)
		{
			Item item = inventory.getItem();
			if (item instanceof ItemBlock)
			{
				return ((ItemBlock) item).getBlock();
			}
		}
		return null;
	}
	
    @SuppressWarnings("deprecation")
	public IBlockState getDisplayedPlant()
	{
    	Block block = getDisplayedBlock();
    	
		if (block != null)
		{
			Item item = inventory.getItem();
			
			int i = item.getMetadata(inventory.getMetadata());
			IBlockState blockState = block.getStateFromMeta(i);
			
			return blockState;
		}
		
		return null;
	}

    public void setDisplayedItem(ItemStack stack)
	{
		inventory = stack;
	}
	
}
