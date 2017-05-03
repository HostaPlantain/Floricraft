package com.hosta.Floricraft.packet;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketNBT implements IMessage{

	    public NBTTagCompound nbt;

	    public PacketNBT() { }
	    
	    public PacketNBT(NBTTagCompound nbt)
	    {
	        this.nbt = nbt;
	    }

		@Override
		public void fromBytes(ByteBuf bufIn)
		{
	    	PacketBuffer buf = new PacketBuffer(bufIn);
	    	try
	    	{
				this.nbt = buf.readCompoundTag();
			}
	    	catch (IOException e)
	    	{
				e.printStackTrace();
			}
		}

		@Override
		public void toBytes(ByteBuf bufIn)
		{
	    	PacketBuffer buf = new PacketBuffer(bufIn);
	    	buf.writeCompoundTag(nbt);
		}
	}
