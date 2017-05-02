package com.hosta.Floricraft.enchantment;

import com.hosta.Floricraft.Floricraft;
import com.hosta.Floricraft.helper.EntityHelper;
import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentFloric extends EnchantmentBasic {

	public EnchantmentFloric(String name, Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots)
	{
		super(name, rarityIn, typeIn, slots);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
        return enchantmentLevel * 25;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
        return this.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
	
	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level)
	{
		super.onEntityDamaged(user, target, level);
		
		PotionHelper.addPotionEffect(user, FloricraftInit.POTION_FLORIC, level - 1, 200);
		Floricraft.proxy.spawnFloricParticle(target.worldObj, EntityHelper.randomPosInEntityBoundingBox(target), 12, 1);
	}
}
