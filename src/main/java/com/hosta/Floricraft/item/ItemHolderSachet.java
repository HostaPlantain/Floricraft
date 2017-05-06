package com.hosta.Floricraft.item;

import com.hosta.Floricraft.Floricraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemHolderSachet extends ItemHolder {

	public ItemHolderSachet(String name)
	{
		super(name);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
        playerIn.openGui(Floricraft.instance, 1, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(entityIn instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) entityIn;
			if(!stack.hasTagCompound())
	        {
				stack.setTagCompound(new NBTTagCompound());
	        }
	        NBTTagList tags = stack.getTagCompound().hasKey("Items") ? (NBTTagList)stack.getTagCompound().getTag("Items") : new NBTTagList();
	        stack.getTagCompound().removeTag("Items");

	        for(int i = 0; i < tags.tagCount(); i++)
	        {
	            NBTTagCompound tagCompound = tags.getCompoundTagAt(i);
	            int slot = tagCompound.hasKey("Slot") ? tagCompound.getByte("Slot") : -999;
		        if(slot >= 0 && slot < 9)
		        {
		         	ItemStack sachet = new ItemStack(tagCompound);
		           	sachet.getItem().onUpdate(sachet, worldIn, player, itemSlot, false);
		           	
			        tags.removeTag(i);
			        tagCompound = sachet.getTagCompound() != null && sachet.getTagCompound().hasKey("broken") ? (NBTTagCompound) sachet.getTagCompound().getTag("broken") : sachet.writeToNBT(new NBTTagCompound());
		           	tagCompound.setByte("Slot", (byte)slot);
		           	tags.appendTag(tagCompound);
		        }
	        }
	        
	        stack.getTagCompound().setTag("Items", tags);
			player.inventory.setInventorySlotContents(itemSlot, stack);
		}
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
