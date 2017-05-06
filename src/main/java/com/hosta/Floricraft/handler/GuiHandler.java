package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.client.gui.GuiHolder;
import com.hosta.Floricraft.inventory.ContainerHolder;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerHolder(player, ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiHolder(player, ID);
	}
}
