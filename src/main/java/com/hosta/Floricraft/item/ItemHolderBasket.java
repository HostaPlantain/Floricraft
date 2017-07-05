package com.hosta.Floricraft.item;

import com.hosta.Floricraft.Floricraft;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHolderBasket extends ItemHolder {

	private int id;
	
	public ItemHolderBasket(String name, int ID)
	{
		super(name);
		this.id = ID;
		this.setHasSubtypes(true);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
        playerIn.openGui(Floricraft.instance, id, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    protected static String getNameFromMeta(int meta)
    {
		return meta == 0 ? "_empty" : "";
    }
	
	public static void preRegisterRender(Item item)
	{
		Registerer.registerRender(item, 0, item.getUnlocalizedName().substring(5) + getNameFromMeta(0));
		Registerer.registerRender(item, 1, item.getUnlocalizedName().substring(5) + getNameFromMeta(1));
	}
}
