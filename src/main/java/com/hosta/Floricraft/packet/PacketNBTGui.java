package com.hosta.Floricraft.packet;

import net.minecraft.nbt.NBTTagCompound;

public class PacketNBTGui extends PacketNBT{

    public PacketNBTGui() { }

    public PacketNBTGui(NBTTagCompound nbt)
    {
    	super(nbt);
    }
}
