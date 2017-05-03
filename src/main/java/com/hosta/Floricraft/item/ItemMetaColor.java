package com.hosta.Floricraft.item;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemMetaColor extends ItemBasic {
	
	public ItemMetaColor(String unlocalizedName)
	{
		super(unlocalizedName);
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items)
	{
		items.add(new ItemStack(item, 1, 0));
		items.add(new ItemStack(item, 1, 1));
		items.add(new ItemStack(item, 1, 2));
		items.add(new ItemStack(item, 1, 3));
		items.add(new ItemStack(item, 1, 4));
		items.add(new ItemStack(item, 1, 5));
		items.add(new ItemStack(item, 1, 6));
		items.add(new ItemStack(item, 1, 7));
		items.add(new ItemStack(item, 1, 8));
		items.add(new ItemStack(item, 1, 9));
		items.add(new ItemStack(item, 1, 10));
		items.add(new ItemStack(item, 1, 11));
		items.add(new ItemStack(item, 1, 12));
		items.add(new ItemStack(item, 1, 13));
		items.add(new ItemStack(item, 1, 14));
		items.add(new ItemStack(item, 1, 15));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if(stack.getItemDamage() == 0)
		{
			return this.getUnlocalizedName() + "." + "black";
		}
		if(stack.getItemDamage() == 1)
		{
			return this.getUnlocalizedName() + "." + "red";
		}
		if(stack.getItemDamage() == 2)
		{
			return this.getUnlocalizedName() + "." + "green";
		}
		if(stack.getItemDamage() == 3)
		{
			return this.getUnlocalizedName() + "." + "brown";
		}
		if(stack.getItemDamage() == 4)
		{
			return this.getUnlocalizedName() + "." + "blue";
		}
		if(stack.getItemDamage() == 5)
		{
			return this.getUnlocalizedName() + "." + "purple";
		}
		if(stack.getItemDamage() == 6)
		{
			return this.getUnlocalizedName() + "." + "cyan";
		}
		if(stack.getItemDamage() == 7)
		{
			return this.getUnlocalizedName() + "." + "light_gray";
		}
		if(stack.getItemDamage() == 8)
		{
			return this.getUnlocalizedName() + "." + "gray";
		}
		if(stack.getItemDamage() == 9)
		{
			return this.getUnlocalizedName() + "." + "pink";
		}
		if(stack.getItemDamage() == 10)
		{
			return this.getUnlocalizedName() + "." + "lime";
		}
		if(stack.getItemDamage() == 11)
		{
			return this.getUnlocalizedName() + "." + "yellow";
		}
		if(stack.getItemDamage() == 12)
		{
			return this.getUnlocalizedName() + "." + "light_blue";
		}
		if(stack.getItemDamage() == 13)
		{
			return this.getUnlocalizedName() + "." + "magenta";
		}
		if(stack.getItemDamage() == 14)
		{
			return this.getUnlocalizedName() + "." + "orange";
		}
		if(stack.getItemDamage() == 15)
		{
			return this.getUnlocalizedName() + "." + "white";
		}
		return "void";
	}
	
	public static void preRegisterRender(Item item)
	{
		Registerer.registerRender(item, 0, item.getUnlocalizedName().substring(5) + "_black");
		Registerer.registerRender(item, 1, item.getUnlocalizedName().substring(5) + "_red");
		Registerer.registerRender(item, 2, item.getUnlocalizedName().substring(5) + "_green");
		Registerer.registerRender(item, 3, item.getUnlocalizedName().substring(5) + "_brown");
		Registerer.registerRender(item, 4, item.getUnlocalizedName().substring(5) + "_blue");
		Registerer.registerRender(item, 5, item.getUnlocalizedName().substring(5) + "_purple");
		Registerer.registerRender(item, 6, item.getUnlocalizedName().substring(5) + "_cyan");
		Registerer.registerRender(item, 7, item.getUnlocalizedName().substring(5) + "_light_gray");
		Registerer.registerRender(item, 8, item.getUnlocalizedName().substring(5) + "_gray");
		Registerer.registerRender(item, 9, item.getUnlocalizedName().substring(5) + "_pink");
		Registerer.registerRender(item, 10, item.getUnlocalizedName().substring(5) + "_lime");
		Registerer.registerRender(item, 11, item.getUnlocalizedName().substring(5) + "_yellow");
		Registerer.registerRender(item, 12, item.getUnlocalizedName().substring(5) + "_light_blue");
		Registerer.registerRender(item, 13, item.getUnlocalizedName().substring(5) + "_magenta");
		Registerer.registerRender(item, 14, item.getUnlocalizedName().substring(5) + "_orange");
		Registerer.registerRender(item, 15, item.getUnlocalizedName().substring(5) + "_white");
	}
	
	public static void preRegisteryModelBakeryStuff(Item item)
	{
		ModelBakery.registerItemVariants
		(
				item,
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_black"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_red"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_green"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_brown"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_blue"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_purple"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_cyan"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_light_gray"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_gray"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_pink"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_lime"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_yellow"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_light_blue"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_magenta"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_orange"),
				new ResourceLocation(Reference.MOD_ID, item.getUnlocalizedName().substring(5) + "_white")
		);
	}
}
