package com.hosta.Floricraft.world.biome;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeBasicWithPath extends BiomeBasic {

HashMap<BlockPos, int[]> hightMap = new HashMap<BlockPos, int[]>();
	
	public BiomeBasicWithPath(BiomeProperties properties)
	{
		super(properties.setBaseHeight(0.125F).setHeightVariation(0.0F));
		theBiomeDecorator.generateLakes = false;
		theBiomeDecorator.sandPerChunk2 = 0;
		theBiomeDecorator.clayPerChunk = 0;
		theBiomeDecorator.sandPerChunk = 0;
		theBiomeDecorator.flowersPerChunk = 0;
		theBiomeDecorator.grassPerChunk = 0;
		theBiomeDecorator.treesPerChunk = -1;
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
	}
}
