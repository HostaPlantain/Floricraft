package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftTabs;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMetaFlower extends ItemBasic {
	
	public ItemMetaFlower(String unlocalizedName)
	{
		super(unlocalizedName);
		this.setHasSubtypes(true);
	}
	
	public static int max_meta = 15;
	
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (tab == FloricraftTabs.tabFloricraft)
		{
			for(int i = 0; i < ItemMetaFlower.max_meta; i++)
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
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    protected static String getNameFromMeta(int meta)
    {
    	switch (meta)
    	{
    		case 0:		return "dandelion";
    		case 1:		return "poppy";
			case 2:		return "blue_orchid";
			case 3:		return "allium";
			case 4:		return "azure_bluet";
			case 5:		return "red_tulip";
			case 6:		return "orange_tulip";
			case 7:		return "white_tulip";
			case 8:		return "pink_tulip";
			case 9:		return "oxeye_daisy";
			case 10:	return "sunflower";
			case 11:	return "lilac";
			default:
			case 12:	return "rose";
			case 13:	return "peony";
			case 14:	return "sakura";
    	}
    }

    public static int getColorIDFromMeta(int meta)
    {
    	switch (meta)
    	{
    		case 0:		return 11;
    		case 1:		return 1;
			case 2:		return 4;
			case 3:		return 13;
			case 4:		return 15;
			case 5:		return 1;
			case 6:		return 14;
			case 7:		return 15;
			case 8:		return 9;
			case 9:		return 15;
			case 10:	return 11;
			case 11:	return 13;
			default:
			case 12:	return 1;
			case 13:	return 13;
			case 14:	return 9;
    	}
    }

    public static int getMetaFromName(String name)
    {
    	for(int i = 0; i < ItemMetaFlower.max_meta; i++)
		{
    		if (getNameFromMeta(i).equals(name))
    		{
    			return i;
    		}
		}
    	return -1;
    }
    
	public static void preRegisterRender(Item item)
	{
		for(int i = 0; i < ItemMetaFlower.max_meta; i++)
		{
			Registerer.registerRender(item, i, item.getUnlocalizedName().substring(5) + "_" + getNameFromMeta(i));
		}
	}
}
