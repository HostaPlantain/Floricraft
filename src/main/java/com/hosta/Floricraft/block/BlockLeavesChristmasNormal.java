package com.hosta.Floricraft.block;

import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;

import net.minecraft.item.ItemStack;

public class BlockLeavesChristmasNormal extends BlockLeavesChristmas implements IMetaBlockName {

	public BlockLeavesChristmasNormal(String name, float lightLevel)
	{
		super(name, lightLevel);
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumVariant.getSpecialName(stack.getItemDamage() % EnumVariant.getMaxMeta());
	}
}
