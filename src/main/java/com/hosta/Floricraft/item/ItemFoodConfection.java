package com.hosta.Floricraft.item;

import java.util.List;

import com.hosta.Floricraft.helper.PotionHelper;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFoodConfection extends ItemBasicFood {

	public ItemFoodConfection(String unlocalizedName, int amount, float saturation)
	{
		super(unlocalizedName, amount, saturation, false);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 8;
	}
	
	private static int max_meta = 3;
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		PotionHelper.addPotionEffect(player, MobEffects.LUCK, 0, 100);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> items)
	{
		for(int i = 0; i < ItemFoodConfection.max_meta; i++)
		{
			items.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocalizedName() + "." + "type" + stack.getItemDamage();
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    public static void preRegisterRender(Item item)
	{
    	for(int i = 0; i < ItemFoodConfection.max_meta; i++)
		{
    		Registerer.registerRender(item, i, item.getUnlocalizedName().substring(5) + "_" + "type" + i);
		}
	}
}
