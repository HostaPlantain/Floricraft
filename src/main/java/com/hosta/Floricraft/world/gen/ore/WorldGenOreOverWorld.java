package com.hosta.Floricraft.world.gen.ore;

import java.util.Random;

import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOreOverWorld extends WorldGenOre {

	private final WorldGenerator GEN_SALT_ORE = new WorldGenMinable(FloricraftInit.ORE_SALT.getDefaultState(), 8);
	private final WorldGenerator GEN_SALT_BLOCK = new WorldGenMinable(FloricraftInit.BLOCK_SALT.getDefaultState(), 16);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.getDimension() == 0)
		{
			this.runGenerator(this.GEN_SALT_ORE, world, random, chunkX, chunkZ, 16, 0, 56);
			
			if (world.getBiomeForCoordsBody(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)) instanceof BiomeOcean)
			{
				this.runGenerator(this.GEN_SALT_BLOCK, world, random, chunkX, chunkZ, 16, 8, 48);
			}
	    }
	}
}
