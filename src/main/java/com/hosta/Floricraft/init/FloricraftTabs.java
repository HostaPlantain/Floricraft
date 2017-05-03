package com.hosta.Floricraft.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class FloricraftTabs extends CreativeTabs {

	public FloricraftTabs(String label)
	{
		super(label);
	}

	public static final FloricraftTabs tabFloricraft = new FloricraftTabs("tabFloricraft")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(FloricraftInit.SACHET_TEMPTATION);
        }
    };
}
