package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftTabs;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMetaColor extends ItemBasic {
	
	public ItemMetaColor(String unlocalizedName)
	{
		super(unlocalizedName);
		this.setHasSubtypes(true);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (tab == FloricraftTabs.tabFloricraft)
		{
			for(int i = 0; i < 16; i++)
			{
				items.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocalizedName() + "." + getNameFromMeta(stack.getItemDamage());
	}

    protected static String getNameFromMeta(int meta)
    {
    	switch (meta)
    	{
    		case 0:		return "black";
			default:
    		case 1:		return "red";
			case 2:		return "green";
			case 3:		return "brown";
			case 4:		return "blue";
			case 5:		return "purple";
			case 6:		return "cyan";
			case 7:		return "light_gray";
			case 8:		return "gray";
			case 9:		return "pink";
			case 10:	return "lime";
			case 11:	return "yellow";
			case 12:	return "light_blue";
			case 13:	return "magenta";
			case 14:	return "orange";
			case 15:	return "white";
    	}
    }

	public static void preRegisterRender(Item item)
	{
		for(int i = 0; i < 16; i++)
		{
			Registerer.registerRender(item, i, item.getUnlocalizedName().substring(5) + "_" + getNameFromMeta(i));
		}
	}
}
