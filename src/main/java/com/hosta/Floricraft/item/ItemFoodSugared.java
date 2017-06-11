package com.hosta.Floricraft.item;

import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFoodSugared extends ItemBasicFood {

	public ItemFoodSugared(String unlocalizedName, int amount, float saturation)
	{
		super(unlocalizedName, amount, saturation, false);
		this.setHasSubtypes(true);
		setAlwaysEdible();
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 8;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if(stack.getItem() == FloricraftInit.PETAL_SUGARED)
		{
			PotionHelper.addPotionEffect(player, FloricraftInit.POTION_FLORIC, 0, 200);
		}
		
		if(stack.getItem() == FloricraftInit.PETALS_SUGARED)
		{
			PotionHelper.addPotionEffect(player, FloricraftInit.POTION_FLORIC, 3, 200);
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < ItemMetaFlower.max_meta; i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocalizedName() + "." + ItemMetaFlower.getNameFromMeta(stack.getItemDamage());
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
}
