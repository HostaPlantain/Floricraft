package com.hosta.Floricraft.client.handler;

import com.hosta.Floricraft.client.gui.GuiImage;
import com.hosta.Floricraft.client.render.block.ColorRegisterer;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

	public static GuiImage eventMessage = null;
	
	@SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event)
    {
		if (eventMessage != null)
		{
			if (event.getType() == ElementType.EXPERIENCE)
			{
				eventMessage.renderCenteredImage();
			}
			
			if (eventMessage.isUnNeeded())
			{
				eventMessage = null;
			}
		}
    }
	
	@SubscribeEvent
	public void onButtonPressed(GuiScreenEvent.ActionPerformedEvent.Pre event)
	{
		ColorRegisterer.setGraphicsLevel(FloricraftInit.LEAVES_FLORIC_TYPE0);
		ColorRegisterer.setGraphicsLevel(FloricraftInit.LEAVES_CHRISTMAS);
		ColorRegisterer.setGraphicsLevel(FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC);
		ColorRegisterer.setGraphicsLevel(FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT);
		ColorRegisterer.setGraphicsLevel(FloricraftInit.LEAVES_CHRISTMAS_UNLIT);
	}
}
