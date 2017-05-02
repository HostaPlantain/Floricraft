package com.hosta.Floricraft.item;

import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArmorCloth extends ItemBasicArmor{

	public ItemArmorCloth(String unlocalizedName, ArmorMaterial armorMaterial, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(unlocalizedName, armorMaterial, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if(isArmored(player, 38, this))
		{
			if(isArmored(player, 39, FloricraftInit.CLOTH_HELMET) && isArmored(player, 37, FloricraftInit.CLOTH_LEGGINGS) && isArmored(player, 36, FloricraftInit.CLOTH_BOOTS))
			{
				PotionHelper.addPotionEffect(player, MobEffects.SPEED, 1);
			}
		}
	}
	
	public boolean isArmored(EntityPlayer player, int armorSlot, Item armor)
	{
		if(player.inventory.getStackInSlot(armorSlot) != null && player.inventory.getStackInSlot(armorSlot).getItem() == armor)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
