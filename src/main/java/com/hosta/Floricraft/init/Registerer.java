package com.hosta.Floricraft.init;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.item.ItemBlockMeta;
import com.hosta.Floricraft.item.ItemBlockMetaWood;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Registerer {
	
	//Register
	public static void register(Item item)
	{
		GameRegistry.register(item, new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5)));
	}
	
	public static void register(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public static void registerWithMeta(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlockMeta(block).setRegistryName(block.getRegistryName()));
	}

	public static void registerWithMetaWood(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlockMetaWood(block).setRegistryName(block.getRegistryName()));
	}

	public static void registerWithTileEntity(Block block, Class<? extends TileEntity> tileEntityClass)
	{
		Registerer.register(block);
		GameRegistry.registerTileEntity(tileEntityClass, block.getUnlocalizedName());
	}

	public static void registerWithTileEntityWithMeta(Block block, Class<? extends TileEntity> tileEntityClass)
	{
		Registerer.registerWithMeta(block);
		GameRegistry.registerTileEntity(tileEntityClass, block.getUnlocalizedName());
	}
	
	public static void registerBlockWithOutItem(Block block)
	{
		GameRegistry.register(block);
	}

	public static void register(Potion potion)
	{
		GameRegistry.register(potion, new ResourceLocation(Reference.MOD_ID, potion.getName()));
	}

	public static void register(Enchantment enchantment)
	{
		GameRegistry.register(enchantment, new ResourceLocation(Reference.MOD_ID, enchantment.getName()));
	}

	public static void register(Biome biome)
	{
		GameRegistry.register(biome, new ResourceLocation(Reference.MOD_ID, biome.getBiomeName()));
	}

	public static void register(IRecipe recipe)
	{
		GameRegistry.register(recipe);
	}

	//Register Render
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}	
	
	public static void registerRender(Item item, int meta, String fileName)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			.register(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + fileName, "inventory"));
	}	
	
	public static void registerRender(Block block)
	{
		registerRender(Item.getItemFromBlock(block));
	}
	
	public static void registerRender(Block block, int meta, String fileName)
	{
		registerRender(Item.getItemFromBlock(block), meta, fileName);
	}
}
