package com.hosta.Floricraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public final class TileEntityPotPourri extends TileEntity implements ITickable {
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update()
	{
		System.out.println("Hello, I'm a TileEntity!");
	}
}
