package com.hosta.Floricraft.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionHelper {

	public static void decreaseBadPotionEffect(EntityLivingBase entity, PotionEffect potionEffectIn)
	{
		Potion potion = potionEffectIn.getPotion();
		if(potion.isBadEffect())
		{
			PotionEffect potionEffect;
			if(potionEffectIn.getAmplifier() != 0 && entity.world.rand.nextInt() == 1)
			{
				potionEffect = new PotionEffect(potion, potionEffectIn.getDuration(), potionEffectIn.getAmplifier() - 1, potionEffectIn.getIsAmbient(), potionEffectIn.doesShowParticles());
			}
			else if(potionEffectIn.getDuration() > 20)
			{
				potionEffect = new PotionEffect(potion, potionEffectIn.getDuration() - 2, potionEffectIn.getAmplifier(), potionEffectIn.getIsAmbient(), potionEffectIn.doesShowParticles());
			}
			else
			{
				return;
			}
			entity.removePotionEffect(potion);
			entity.addPotionEffect(potionEffect);
		}
	}
	
	public static void addEffectToPlayer(Entity entity, Potion potion, int amplifier)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			addPotionEffect(player, potion, amplifier);
		}
	}
	
	public static void addPotionEffect(EntityLivingBase entity, Potion potion, int amplifier)
	{
		addPotionEffect(entity, potion, amplifier, 410);
	}
	
	public static void addPotionEffect(EntityLivingBase entity, Potion potion, int amplifier, int duration)
	{
		if(!entity.isPotionActive(potion)|| entity.getActivePotionEffect(potion).getDuration() < duration|| entity.getActivePotionEffect(potion).getAmplifier() < amplifier)
		{
			entity.addPotionEffect(new PotionEffect(potion, duration, amplifier, true, false));
		}
	}
}
