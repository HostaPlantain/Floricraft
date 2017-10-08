package com.hosta.Floricraft.world.biome;

import java.util.HashMap;
import java.util.Random;

import com.hosta.Floricraft.config.ConfigChecker;
import com.hosta.Floricraft.world.gen.feature.WorldGenSimpleRoad;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeBasicWithPath extends BiomeBasic {

HashMap<BlockPos, int[]> hightMap = new HashMap<BlockPos, int[]>();
	
	public BiomeBasicWithPath(BiomeProperties properties)
	{
		super(properties.setBaseHeight(0.125F).setHeightVariation(0.0F));
		decorator.generateFalls = false;
		decorator.clayPerChunk = -999;
		decorator.gravelPatchesPerChunk = -999;
		decorator.sandPatchesPerChunk = -999;
		decorator.grassPerChunk = -999;
		decorator.flowersPerChunk = -999;
		decorator.treesPerChunk = -999;
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
    	super.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    	
		int x1 = x % 16;
		x1 = x1 < 0 ? x1 + 16 : x1;
		int z1 = z % 16;
		z1 = z1 < 0 ? z1 + 16 : z1;
		int y1 = 255;
		
		while(y1 > 0 && chunkPrimerIn.getBlockState(x1, --y1, z1).getMaterial() == Material.AIR)	{	}
		
		BlockPos chunkPos = new BlockPos(x - x1, 0, z - z1);
		int[] hight = hightMap.containsKey(chunkPos) ? hightMap.get(chunkPos) : new int[256];
		hight[x1 + (z1 * 16)] = y1;
    	hightMap.put(chunkPos, hight);
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		super.decorate(worldIn, rand, pos);
		
		int[] hight = new int[256];
		
		if (hightMap.containsKey(pos))
		{
			hight = hightMap.get(pos);
			hightMap.remove(pos);
			
			for (int x = 0; x < 16; x++)
			{
				for (int z = 0; z < 16; z++)
				{
					int top = hight[x + (z * 16)];
					BlockPos fillerPos = new BlockPos(pos.getX() + x, top, pos.getZ() + z);
						
					while(fillerPos.getY() > 0 && worldIn.isAirBlock(fillerPos))
					{
						worldIn.setBlockState(fillerPos, fillerPos.getY() == top ? topBlock : fillerBlock);
						fillerPos = fillerPos.down();
					}
				}
			}
		}
		
		if (ConfigChecker.getGenBiomeFast())
		{
			genPath(worldIn, pos.getX(), pos.getZ());
		}
	}
	
	public static void genPath(World world, int posX, int posZ)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				BlockPos topPos = new BlockPos(posX + x, 0, posZ + z);
				Biome biome = world.getBiomeForCoordsBody(topPos);
				
				if (biome instanceof BiomeBasicWithPath)
				{
					WorldGenSimpleRoad[] simpleRoads = {new WorldGenSimpleRoad(2.0f, 5.0f, 5.0f, 300), new WorldGenSimpleRoad(-0.5f, 50.0f, 50.0f, 200)};
					topPos = world.getTopSolidOrLiquidBlock(topPos);
					
					boolean pathFlag = false;
					for (WorldGenSimpleRoad simpleRoad: simpleRoads)
					{
						simpleRoad.setSegment(topPos.getX());
						if (simpleRoad.isOnRoad(topPos.getZ()))
						{
							pathFlag = true;
						}
					}
					
					if(pathFlag)
					{
						boolean genFlag = false;
						topPos = topPos.down();

						if (!genFlag)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genPath(world, topPos);
							}
						}
						if (!genFlag)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genGrass(world, topPos);
							}
						}
						if (!genFlag)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genWaterway(world, topPos);
							}
						}
						if (!genFlag && z % 16 != 0)
						{
							for (WorldGenSimpleRoad simpleRoad: simpleRoads)
							{
								genFlag = simpleRoad.genFence(world, topPos);
							}
						}
					}
					else if (biome instanceof BiomeFlowerLand && x % 4 != 0 && z % 16 != 0)
					{
						BiomeFlowerLand biomeFarmer = (BiomeFlowerLand)biome;
						if (world.getBlockState(topPos.down()).getBlock() == biomeFarmer.farmLand.getBlock() && world.isAirBlock(topPos))
						{
							int i = (topPos.getX() / 4) % biomeFarmer.crops.length;
							i = i < 0 ? i + biomeFarmer.crops.length : i;
							
							world.setBlockState(topPos, biomeFarmer.crops[i]);
							if (biomeFarmer.crops[i].getBlock() instanceof BlockDoublePlant)
							{
								world.setBlockState(topPos.up(), biomeFarmer.crops[i].withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER));
							}
						}
					}
				}
			}
		}
	}
}
