package com.hosta.Floricraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityBasic extends Entity {

	public EntityBasic(World worldIn)
	{
		super(worldIn);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		
	}
}
