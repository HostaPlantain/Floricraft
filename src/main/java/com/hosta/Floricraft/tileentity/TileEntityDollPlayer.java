package com.hosta.Floricraft.tileentity;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;

public class TileEntityDollPlayer extends TileEntityDoll {

	private GameProfile playerProfile;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);

	    if (playerProfile != null)
        {
	    	NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTUtil.writeGameProfile(nbttagcompound, this.playerProfile);
            nbt.setTag("Owner", nbttagcompound);
        }

		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);
	    
	    if (nbt.hasKey("Owner"))
        {
            playerProfile = NBTUtil.readGameProfileFromNBT(nbt.getCompoundTag("Owner"));
        }
	}
	
	public void setDisplayedplayer(EntityPlayer player)
	{
		playerProfile = player.getGameProfile();
	}
	
	public GameProfile getDisplayedplayer()
	{
		return playerProfile;
	}
}
