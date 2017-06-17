package com.hosta.Floricraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class RecipeSachet implements IRecipe {

	private final Item ITEM_RESULT;
	private final Item SACHET;
	private final Item[] ITEMS;

	public RecipeSachet(Item result, Item sachet, Item[] items)
	{
		this.ITEM_RESULT = result;
		this.SACHET = sachet;
		this.ITEMS = items;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		Item[] items = this.ITEMS;
		
		for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            
            if (!itemstack.isEmpty())
            {
            	boolean flag = false;
            	
        		for (int j = 0; j < items.length; j++)
        		{
        			if (itemstack.getItem() == items[j])
                    {
        				flag = true;
        				items[j] = null;
                    	break;
                    }
        		}
        		
        		if (!flag)
        		{
        			return false;
        		}
            }
        }
        
		for (int j = 0; j < items.length; j++)
		{
			if (items[j] != null)
			{
				return false;
			}
		}
		
        return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack itemResult = ItemStack.EMPTY;
		
		for (int i = 0; i < inv.getSizeInventory(); i++)
        {
	        ItemStack itemstack = inv.getStackInSlot(i);
	        
	        if (!itemstack.isEmpty() && itemstack.getItem() == this.SACHET)
            {
	        	itemResult = itemstack;
	        	break;
            }
        }
		
		return new ItemStack(this.ITEM_RESULT, 1, itemResult.getItemDamage(), itemResult.getTagCompound());
	}

	@Override
	public boolean func_194133_a(int p_194133_1_, int p_194133_2_)
	{
        return p_194133_1_ >= 3 && p_194133_2_ >= 3;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
        return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		return NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
	}
}
