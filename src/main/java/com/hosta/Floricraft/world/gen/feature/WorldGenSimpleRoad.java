package com.hosta.Floricraft.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WorldGenSimpleRoad {

	private int rare;
	private float slope;
	private float scaleWidth;
	private float scaleHight;
	
	private float[] path;
	private float[] grass;	
	private float[] waterway;
	private float[] fence;
	
	public WorldGenSimpleRoad(float slope, float scaleWidth, float scaleHight, int rare)
	{
		this.rare = rare;
		this.slope = slope;
		this.scaleWidth = scaleWidth;
		this.scaleHight = scaleHight;
	}
	
	private float function(float x)
	{
		return (slope * x) + (MathHelper.cos((x) / scaleWidth) * scaleHight);
	}
	
	public void setSegment(int posX)
	{
		this.path = getSegment(posX, 3);
		path[0] = getRared(path[0]);
		path[1] = getRared(path[1]);
		
		this.grass = getSegmentSide(posX, 3, 1);
		this.waterway = getSegmentSide(posX, 3, 2);
		this.fence = getSegmentSide(posX, 3, 3);
	}
	
	private float[] getSegmentSide(int posX, int width, int side)
	{
		float[] way1 = getSegment(posX - side, width);
		float[] way2 = getSegment(posX + side, width);
		
		return new float[] {getRared(Math.min(way1[0], way2[0]) - side), getRared(Math.max(way1[1], way2[1]) + side)};
	}
	
	private float[] getSegment(int posX, int width)
	{
		float z = function(posX);
		float[] segment = new float[] {z, z};
		float widthHalf = width / 2.0f;
		
		float pos = - widthHalf;
		z = function(posX + pos);
		
		for (int i = 0; i < width; i++)
		{
			float z1 = z;
			z = function(posX + pos + 1);
			
			pos += 0.5f;
			float hight = MathHelper.sqrt_float((widthHalf * widthHalf) - (pos * pos));
			pos += 0.5f;

			segment[0]  = Math.min(Math.min(z1, z) - hight, segment[0]);
			segment[1] = Math.max(Math.max(z1, z) + hight, segment[1]);
		}
		
		return segment;
	}
	
	public boolean isOnRoad(int posZ)
	{
		return isOnPath(posZ, fence[0], fence[1]);
	}

	private boolean isOnPath(int posZ, float min, float max)
	{
		float rareZ = getRared(posZ);
		return (min < max && min <= rareZ && rareZ < max) || (max < min && (rareZ < max || min <= rareZ));
	}
	
	private float getRared(float raw)
	{
		float rared = raw % rare;
		return rared <= 0 ? rared + rare : rared;
	}
	
	public boolean genPath(World world, BlockPos pos)
	{
		if (isOnPath(pos.getZ(), this.path[0], this.path[1]))
		{
			if (world.getBlockState(pos).getBlock() == Blocks.GRASS)
			{
				world.setBlockState(pos, isAirNearBy(world, pos) ? Blocks.WOODEN_SLAB.getDefaultState() : Blocks.GRASS_PATH.getDefaultState());
			}
			return true;
		}
		return false;
	}

	public boolean genGrass(World world, BlockPos pos)
	{
		if (isOnPath(pos.getZ(), this.grass[0], this.path[0]) || isOnPath(pos.getZ(), this.path[1], this.grass[1]))
		{
			if (world.getBlockState(pos).getBlock() == Blocks.GRASS)
			{
				world.setBlockState(pos, Blocks.GRASS.getDefaultState());
				world.setBlockState(pos.up(), Blocks.TALLGRASS.getStateFromMeta(1));
			}
			return true;
		}
		return false;
	}

	public boolean genWaterway(World world, BlockPos pos)
	{
		if (isOnPath(pos.getZ(), this.waterway[0], this.grass[0]) || isOnPath(pos.getZ(), this.grass[1], this.waterway[1]))
		{
			if (world.getBlockState(pos).getBlock() == Blocks.GRASS && world.isAirBlock(pos.up()))
			{
				if (isAirNearBy(world, pos))
				{
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
					pos = pos.down();
				}
				world.setBlockState(pos, Blocks.WATER.getDefaultState());
			}
			return true;
		}
		return false;
	}

	public boolean genFence(World world, BlockPos pos)
	{
		if (isOnPath(pos.getZ(), this.fence[0], this.waterway[0]) || isOnPath(pos.getZ(), this.waterway[1], this.fence[1]))
		{
			if (world.getBlockState(pos).getBlock() == Blocks.GRASS && world.isAirBlock(pos.up()))
			{
				world.setBlockState(pos.up(), Blocks.OAK_FENCE.getDefaultState());
			}
			return true;
		}
		return false;
	}
	
	private boolean isAirNearBy(World world, BlockPos pos)
	{
		return isAirNotOnWater(world, pos.east()) || isAirNotOnWater(world, pos.west()) || isAirNotOnWater(world, pos.south()) || isAirNotOnWater(world, pos.north());
	}
	
	private boolean isAirNotOnWater(World world, BlockPos pos)
	{
		return isAir(world.getBlockState(pos).getBlock()) && world.isAirBlock(pos.up()) && world.getBlockState(pos.down()).getBlock() != Blocks.WATER;
	}
	
	private boolean isAir(Block block)
	{
		return block == Blocks.AIR || block == Blocks.OAK_FENCE || block == Blocks.TALLGRASS;
	}
}
