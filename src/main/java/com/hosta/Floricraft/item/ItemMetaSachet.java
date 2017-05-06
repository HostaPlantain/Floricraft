package com.hosta.Floricraft.item;

import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class ItemMetaSachet extends ToolBasic {
	
	//ItemStack itemStack;
	
	public ItemMetaSachet(String unlocalizedName/*, ItemStack repair*/)
	{
		super(unlocalizedName);
		this.setMaxDamage(7200);
		//this.itemStack = repair; 
	}
	
	@Override
	public boolean isDamageable()
	{
		return true;
	}
	
	/*
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return repair.isItemEqual(itemStack) || super.getIsRepairable(toRepair, repair);
	}
	*/
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
    {
		if(entityIn instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) entityIn ;
			{
				checkAndAddEffectPlayer(stack, this, player, FloricraftInit.POTION_FLORIC, 0);
				checkAndAddEffectPlayer(stack, FloricraftInit.SACHET_TEMPTATION, player, FloricraftInit.POTION_TEMPTATION, 0);
				checkAndAddEffectPlayer(stack, FloricraftInit.SACHET_ANTI_ZOMBIE, player, FloricraftInit.POTION_ANTI_ZOMBIE, 0);
				checkAndAddEffectPlayer(stack, FloricraftInit.SACHET_ANTI_SKELETON, player, FloricraftInit.POTION_ANTI_SKELETON, 0);
				checkAndAddEffectPlayer(stack, FloricraftInit.SACHET_ANTI_CREEPER, player, FloricraftInit.POTION_ANTI_CREEPER, 0);
				checkAndAddEffectPlayer(stack, FloricraftInit.SACHET_ANTI_SPIDER, player, FloricraftInit.POTION_ANTI_SPIDER, 0);
				checkAndAddEffectPlayer(stack, FloricraftInit.SACHET_ANTI_ENDERMAN, player, FloricraftInit.POTION_ANTI_ENDERMAN, 0);
			}
			
			damageItem(stack, player, itemSlot);
			super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		}
    }
    
	protected void checkAndAddEffectPlayer(ItemStack stack, Item sachet, EntityPlayer player, Potion potion, int amplifier)
	{
    	if(!player.world.isRemote && stack.getItem() == sachet)
    	{
    		PotionHelper.addPotionEffect(player, potion, amplifier);
    	}
	}
	
	@SuppressWarnings("deprecation")
	private void damageItem(ItemStack stack, EntityPlayer player, int itemSlot)
	{
		if (stack.getItemDamage() < this.getMaxDamage())
		{
			if(player.ticksExisted % 20 == 0)
			{
				stack.damageItem(1, player);
			}
		}
		else if (stack.getItemDamage() == this.getMaxDamage())
		{
			player.renderBrokenItemStack(stack);

			ItemStack sachetSac = new ItemStack(FloricraftInit.SACHET);
			sachetSac.setTagCompound(stack.getTagCompound());
			if (player.inventory.getStackInSlot(itemSlot) == stack)
			{
				player.inventory.setInventorySlotContents(itemSlot, sachetSac);
			}
			else
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setTag("broken", sachetSac.writeToNBT(new NBTTagCompound()));
				stack.setTagCompound(nbt);
			}
		}
		else
		{
			stack.setItemDamage(3600);
		}
	}
}
