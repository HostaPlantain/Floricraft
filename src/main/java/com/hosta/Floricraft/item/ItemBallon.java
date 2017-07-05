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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		if (!worldIn.isRemote)
		{
			worldIn.spawnEntityInWorld(new EntityBallon(worldIn, (byte) playerIn.getHeldItem(hand).getMetadata(), playerIn.posX + playerIn.getLookVec().xCoord, playerIn.posY + playerIn.getLookVec().yCoord + 1.0D, playerIn.posZ + playerIn.getLookVec().zCoord));
		}
		
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}
