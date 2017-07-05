package com.hosta.Floricraft.tileentity;

import java.util.List;

import com.hosta.Floricraft.block.BlockFlowerLycoris;
import com.hosta.Floricraft.helper.EntityHelper;
import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ToolSachet;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileEntityDoll extends TileEntityBasicWithRender implements ITickable{
	
	private ItemStack inventory;
	
    public void onClick(EntityPlayer player, ItemStack stackIn, EnumHand hand)
    {
        if (this.getDisplayedItem() == null && stackIn != null)
        {
            if (!this.worldObj.isRemote)
            {
            	player.setHeldItem(hand, null);
            }
            this.setDisplayedItem(stackIn);
    		this.markDirty();
        }
        else if (this.getDisplayedItem() != null && stackIn == null)
        {
        	if (!this.worldObj.isRemote)
        	{
        		ItemHandlerHelper.giveItemToPlayer(player, this.inventory);
        	}
            this.setDisplayedItem(stackIn);
    		this.markDirty();
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
	
	@Override
	public void update()
	{
		if (!this.worldObj.isRemote && inventory != null)
		{
			Item item = inventory.getItem();
			if (item instanceof ToolSachet)
			{
	    		if(inventory.getItemDamage() < inventory.getMaxDamage())
	    		{
	    			checkAndAddEffectPlayer(item, item, FloricraftInit.POTION_FLORIC, 0);
	    			checkAndAddEffectPlayer(item, FloricraftInit.SACHET_TEMPTATION, FloricraftInit.POTION_TEMPTATION, 0);
	    			checkAndAddEffectPlayer(item, FloricraftInit.SACHET_ANTI_ZOMBIE, FloricraftInit.POTION_ANTI_ZOMBIE, 0);
	    			checkAndAddEffectPlayer(item, FloricraftInit.SACHET_ANTI_SKELETON, FloricraftInit.POTION_ANTI_SKELETON, 0);
	    			checkAndAddEffectPlayer(item, FloricraftInit.SACHET_ANTI_CREEPER, FloricraftInit.POTION_ANTI_CREEPER, 0);
	    			checkAndAddEffectPlayer(item, FloricraftInit.SACHET_ANTI_SPIDER, FloricraftInit.POTION_ANTI_SPIDER, 0);
	    			checkAndAddEffectPlayer(item, FloricraftInit.SACHET_ANTI_ENDERMAN, FloricraftInit.POTION_ANTI_ENDERMAN, 0);
	    			
	    			if (((int)this.worldObj.getTotalWorldTime()) % 40 == 0)
	    			{
	    				inventory.attemptDamageItem(1, worldObj.rand);
	    			}
	    		}
	    		else
	    		{
	    			this.setDisplayedItem(new ItemStack(FloricraftInit.SACHET));
	    			this.markDirty();
	    		}
			}
			else if (item instanceof ItemBlock)
			{
				Block block = ((ItemBlock)item).getBlock();
				if (block instanceof BlockFlowerLycoris)
				{
					AxisAlignedBB aabb = this.getAxisAlignedBB().expand(16, 4, 16);
					List<Entity> list = this.getWorld().getEntitiesWithinAABBExcludingEntity(null, aabb);
					list.forEach(entity -> EntityHelper.proEntity(entity, EntityZombie.class, pos));
				}
			}
		}
	}
	
	private void checkAndAddEffectPlayer(Item item, Item sachet, Potion potion, int amplifier)
	{
		if (item == sachet)
		{
			AxisAlignedBB aabb = this.getAxisAlignedBB().expand(16, 4, 16);
			List<Entity> list = this.getWorld().getEntitiesWithinAABBExcludingEntity(null, aabb);
			list.forEach(entity -> PotionHelper.addEffectToPlayerOnly(entity, potion, amplifier));
		}
	}

	public ItemStack getDisplayedItem()
	{
		return inventory;
	}

    private void setDisplayedItem(ItemStack stack)
	{
		inventory = stack;
	}
}
