package com.hosta.Floricraft.client.render;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.client.render.block.ColorRegisterer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class RenderRegisterer {

	public static void registerRender(Item item, int meta, String fileName)
	{
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + fileName, "inventory"));
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + fileName, "inventory"));
	}
	
	public static void registerLeaves(Block block)
	{
		ColorRegisterer.registerLeaves(block);
	}

	public static void registerGrass(Block block)
	{
		ColorRegisterer.registerGrass(block);
	}
	
	public static <T extends TileEntity> void registerRender(Class<T> tileEntity, TileEntitySpecialRenderer<T> renderer)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntity, renderer);
	}
}
