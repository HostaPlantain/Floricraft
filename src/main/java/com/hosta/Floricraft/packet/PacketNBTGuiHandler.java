package com.hosta.Floricraft.packet;

import com.hosta.Floricraft.client.gui.GuiImage;
import com.hosta.Floricraft.client.handler.ClientEventHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketNBTGuiHandler implements IMessageHandler<PacketNBTGui, PacketNBTGui>{

	@SideOnly(Side.CLIENT)
	@Override
	public PacketNBTGui onMessage(PacketNBTGui message, MessageContext ctx)
	{
		NBTTagCompound nbt = message.nbt;
		
		if (nbt.hasKey("Text") && nbt.hasKey("Tick"))
		{
			String text = nbt.getString("Text");
			int tick = nbt.getInteger("Tick");
			
			ClientEventHandler.eventMessage = new GuiImage(text, tick);
		}
		return null;
	}
}
