package com.hosta.Floricraft.world.gen.feature;

import java.util.Random;

import com.hosta.Floricraft.item.ItemBlockMetaWood;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTreeFloric extends WorldGenAbstractTree {

	private IBlockState log;
	private IBlockState leaves;
	
	private final int[][][] LEAVES = new int[][][] {{{}}, {{1, 0}}, {{1, 0}, {1, 1}}, {{1, 0}, {1, 1}, {2, 0}}, {{}}, {{1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}}, {{1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {3, 0}}, {{}}, {{}}, {{1, 0}, {1, 1}, {1, 2}, {1, 3}, {2, 0}, {2, 1}, {2, 2}, {3, 0}, {3, 1}}}; 
	
	private final int[] NARROW_TREE4 = new int[] {0, 1, 2, 2, 1};
	private final int[] NORMAL_TREE5 = new int[] {0, 3, 5, 5, 3, 1};
	private final int[] NORMAL_TREE6 = new int[] {0, 0, 3, 5, 5, 3, 1};
	private final int[] NORMAL_TREE6B = new int[] {0, 0, 5, 3, 5, 3, 1};
	private final int[] BIG_TREE7 = new int[] {0, 0, 3, 9, 9, 6, 3, 1};

	public WorldGenTreeFloric(boolean notify, IBlockState log, IBlockState leaves)
	{
		super(notify);
		this.log = log;
		this.leaves = leaves;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		int i = rand.nextInt(4);
		switch (i)
		{
			case 0:	return generateTree(worldIn, position, NORMAL_TREE5);
			
			case 1: return generateTree(worldIn, position, NORMAL_TREE6);
				
			default:	
				switch (ItemBlockMetaWood.getSpecialName(leaves))
				{
					case "azalea": return generateTree(worldIn, position, NARROW_TREE4);
					
					case "dogwood":
						if (i == 2) return generateTree(worldIn, position, NARROW_TREE4);
						return generateTree(worldIn, position, NORMAL_TREE6B);
						
					default:
					case "kerria":
						if (i == 2) return generateTree(worldIn, position, NORMAL_TREE6B);
						return generateTree(worldIn, position, BIG_TREE7);
					
					case "sakura":
						if (i == 2) return generateTree(worldIn, position, BIG_TREE7);
						return generateTree(worldIn, position, BIG_TREE7);
				}
		}
	}

	private boolean canGrow(World worldIn, BlockPos position, int[] hight)
	{
		if (worldIn.getBlockState(position.down()).getMaterial() != Material.GRASS && worldIn.getBlockState(position.down()).getMaterial() != Material.GROUND)
		{
			return false;
		}
			
		for (int i = 1; i < hight.length; i++)
		{
			if (!worldIn.isAirBlock(position.up(i)))
			{
				return false;
			}
		}
		return true;
	}

	private boolean generateTree(World worldIn, BlockPos position, int[] hight)
	{
		if (canGrow(worldIn, position, hight))
		{
			for (int i = 0; i < hight.length; i++)
			{
				worldIn.setBlockState(position.up(i), log);
				if (hight[i] != 0)
				{
					int[][] posLeaves = LEAVES[hight[i]];
					for (int j = 0; j < posLeaves.length; j++)
					{
						setLeave(worldIn, new BlockPos(position.add(posLeaves[j][0], i, posLeaves[j][1])));
						setLeave(worldIn, new BlockPos(position.add(-posLeaves[j][0], i, -posLeaves[j][1])));
						setLeave(worldIn, new BlockPos(position.add(posLeaves[j][1], i, -posLeaves[j][0])));
						setLeave(worldIn, new BlockPos(position.add(-posLeaves[j][1], i, posLeaves[j][0])));
					}
				}
			}
			setLeave(worldIn, position.up(hight.length - 1));
			return true;
		}
		return false;
	}

	private void setLeave(World worldIn, BlockPos position)
	{
		if (isReplaceable(worldIn, position))
		{
			worldIn.setBlockState(position, leaves);
		}
	}
}
