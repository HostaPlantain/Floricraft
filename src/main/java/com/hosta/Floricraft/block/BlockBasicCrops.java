package com.hosta.Floricraft.block;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public abstract class BlockBasicCrops extends BlockCrops {
	
	public BlockBasicCrops(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
	{
		return EnumPlantType.Crop;
	}

	@Override
	protected abstract Item getSeed();
	
	@Override
	protected abstract Item getCrop();
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
		if(isMaxAge(state))
		{
			drops.add(getIncreacedDrop(world, fortune));
		}
		
		super.getDrops(drops, world, pos, state, fortune);
    }
	
	public ItemStack getIncreacedDrop(IBlockAccess world, int fortune)
	{
		Item crop = getCrop();
		Random rand = world instanceof World ? ((World)world).rand : RANDOM;
        int count = rand.nextInt(2) + 1;
        
        if(fortune > 0)
        {
        	count += rand.nextInt(fortune);
        }
        
		return new ItemStack(crop, count);
	}
}
