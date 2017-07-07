package com.hosta.Floricraft.tileentity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TileEntityBasicWithRender extends TileEntityBasic {

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag)
	{
		super.handleUpdateTag(tag);
		this.readFromNBT(tag);
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 8, this.getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		this.sendPacket();
	}
	
	private void sendPacket()
	{
		if (!this.getWorld().isRemote)
		{
			for (EntityPlayer player : this.getWorld().playerEntities)
	        {
				((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
	        }
		}		
	}
}
