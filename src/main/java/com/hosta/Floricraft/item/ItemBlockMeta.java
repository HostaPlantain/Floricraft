package com.hosta.Floricraft.item;

import com.hosta.Floricraft.block.IMetaBlockName;
import com.hosta.Floricraft.init.Registerer;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMeta extends ItemBlock {
	
	public ItemBlockMeta(Block block)
	{
        super(block);
        if(!(block instanceof IMetaBlockName))
        {
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of ISpecialBlockName!", block.getUnlocalizedName()));
        }
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
        return super.getUnlocalizedName(stack) + "." + ((IMetaBlockName)this.block).getSpecialName(stack);
    }
    
    @Override
    public String getUnlocalizedName()
    {
    	return super.getUnlocalizedName() + "." + ((IMetaBlockName)this.block).getSpecialName(new ItemStack(this.block));
    }

	public static void preRegisterRender(Block block)
	{
		for(int i = 0; i < 16 ; i++)
		{
			Registerer.registerRender(block, i, block.getUnlocalizedName().substring(5) + "_" + ((IMetaBlockName)block).getSpecialName(new ItemStack(block, 1 , i)));
		}
	}
}
