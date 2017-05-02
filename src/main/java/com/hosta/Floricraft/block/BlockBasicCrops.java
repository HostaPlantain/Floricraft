package com.hosta.Floricraft.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public abstract class BlockBasicCrops extends BlockCrops {
	
	public BlockBasicCrops(String name)
	{
		super();
		this.setUnlocalizedName(name).setRegistryName(name);
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
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		
		if(isMaxAge(state))
		{
			ret.add(getIncreacedDrop(world, fortune));
		}
		
		return ret;
	}
	
	public ItemStack getIncreacedDrop(IBlockAccess world, int fortune)
	{
		Item crop = getCrop();
		Random rand = world instanceof World ? ((World)world).rand : new Random();
        int count = rand.nextInt(2) + 1;
        
        if(fortune > 0)
        {
        	count += rand.nextInt(fortune);
        }
        
		return new ItemStack(crop, count);
	}
}
