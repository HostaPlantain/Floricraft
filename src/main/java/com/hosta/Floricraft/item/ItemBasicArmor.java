package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemBasicArmor extends ItemArmor {

	public ItemBasicArmor(String unlocalizedName, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(unlocalizedName).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
}
