package com.hosta.Floricraft.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockStackDead extends BlockStack {
	
	public BlockStackDead(String name, Material materialIn)
	{
		super(name, materialIn);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.STICK;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
        return 2 + random.nextInt(2);
	}
}
