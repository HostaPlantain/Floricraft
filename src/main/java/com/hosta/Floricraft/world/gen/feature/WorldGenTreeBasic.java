package com.hosta.Floricraft.world.gen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenTreeBasic extends WorldGenAbstractTree {

	public WorldGenTreeBasic(boolean notify)
	{
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
    	if (canGrow(worldIn, rand, position))
		{
			this.generateTree(worldIn, rand, position);
			return true;
		}
		return false;
	}

	public abstract boolean canGrow(World worldIn, Random rand, BlockPos position);

	public abstract void generateTree(World worldIn, Random rand, BlockPos position);
	
	public boolean areReplaceable(World worldIn, BlockPos posMin, BlockPos posMax)
	{
		int[] min = new int[]{posMin.getX(), posMin.getY(), posMin.getZ()};
		int[] max = new int[]{posMax.getX(), posMax.getY(), posMax.getZ()};
		
		for (int i = min[0]; i <= max[0]; i++)
		{
			for (int j = min[1]; j <= max[1]; j++)
			{
				for (int k = min[2]; k <= max[2]; k++)
				{
					BlockPos pos = new BlockPos(i, j, k);
					if (!isReplaceable(worldIn, pos))
					{
						return false;
					}
				}
			}
		}
		
		for (int i = min[0]; i <= max[0]; i++)
		{
			for (int k = min[2]; k <= max[2]; k++)
			{
				BlockPos pos = new BlockPos(i, min[1] - 1, k);
				if (!isReplaceable(worldIn, pos) && worldIn.getBlockState(pos).getMaterial() != Material.PLANTS)
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
