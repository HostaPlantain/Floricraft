package com.hosta.Floricraft.helper;

import java.util.Random;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MineHelper {
	
	public static void spawnFireworksAround(World worldIn, BlockPos pos, int range, int count)
	{
		Random rand = worldIn.rand;
		for (int i = 0; i < count; i++)
		{
			double r = rand.nextDouble() * 2 * Math.PI;
			double x = pos.getX() + (Math.cos(r) * (rand.nextInt(range) + range));
			double y = pos.getY() + 2;
			double z = pos.getZ() + (Math.sin(r) * (rand.nextInt(range) + range));
			
			int j = rand.nextInt(3);
			int[] color = {rand.nextInt(256) * 256 * 256 + rand.nextInt(256) * 256 + rand.nextInt(256)};
			
			ItemStack firework = setFirework(rand.nextInt(3), j == 1, j == 2, 0, color);
			
			worldIn.spawnEntity(new EntityFireworkRocket(worldIn, x, y, z, firework));
		}
	}
	
	public static ItemStack setFirework(int flight, boolean flicker, boolean trail, int type, int[] color)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setByte("Flight", (byte) flight);
					
		NBTTagCompound nbtExplosion = new NBTTagCompound();
		nbtExplosion.setBoolean("Flicker", flicker);
		nbtExplosion.setBoolean("Trail", trail);
		nbtExplosion.setByte("Type", (byte) type);
		nbtExplosion.setIntArray("Colors", color);
		
		NBTTagList nbtTagList = new NBTTagList();
		nbtTagList.appendTag(nbtExplosion);
		nbt.setTag("Explosions", nbtTagList);
		
		NBTTagCompound nbtFirework = new NBTTagCompound();
		nbtFirework.setTag("Fireworks", nbt);
		
		ItemStack firework = new ItemStack(new ItemFirework());
		firework.setTagCompound(nbtFirework);
		return firework;
	}
}
