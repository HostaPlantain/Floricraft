package com.hosta.Floricraft.item;

import com.hosta.Floricraft.entity.EntityBallon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBallon extends ItemMetaColor {

	public ItemBallon(String name)
	{
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		if (!worldIn.isRemote)
		{
			worldIn.spawnEntity(new EntityBallon(worldIn, (byte) playerIn.getHeldItem(hand).getMetadata(), playerIn.posX + playerIn.getLookVec().x, playerIn.posY + playerIn.getLookVec().y + 1.0D, playerIn.posZ + playerIn.getLookVec().z));
		}
		
		return super.onItemRightClick(worldIn, playerIn, hand);
	}
}
