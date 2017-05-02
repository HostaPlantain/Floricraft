package com.hosta.Floricraft.item;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBlockMetaWood extends ItemBlock {

	public static final String[] WOOD_TYPE0 = new String[]{"sakura", "dogwood", "kerria", "azalea"};
	
	public ItemBlockMetaWood(Block block)
	{
		super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
	
	@Override
	public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + getSpecialName(stack);
    }
    
    @Override
    public String getUnlocalizedName()
    {
    	return super.getUnlocalizedName() + "." + getSpecialName(new ItemStack(this.block));
    }

    public String getSpecialName(ItemStack stack)
	{
    	return getSpecialName(this.block, stack.getItemDamage());
	}

    public static String getSpecialName(IBlockState state)
	{
    	return getSpecialName(state.getBlock(), state.getBlock().damageDropped(state));
	}
    
    public static String getSpecialName(Block block, int meta)
    {
    	meta = meta <= 0 ? 0 : meta % 4;
    	switch (block.getUnlocalizedName().substring(block.getUnlocalizedName().length() - 1))
		{
			default:
			case "0":
				return WOOD_TYPE0[meta];
		}
    }
    
	public static void preRegisterRender(Block block)
	{
		for(int i = 0; i < 16 ; i++)
		{
			Registerer.registerRender(block, i, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(block, i % 4));
		}
	}
	
	public static void preRegisteryModelBakeryStuff(Block block)
	{
		ModelBakery.registerItemVariants
		(
			Item.getItemFromBlock(block),
			new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(block, 0)),
			new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(block, 1)),
			new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(block, 2)),
			new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(block, 3))
		);
	}
}
