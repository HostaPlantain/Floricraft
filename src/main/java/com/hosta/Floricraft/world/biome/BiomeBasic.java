package com.hosta.Floricraft.world.biome;

import net.minecraft.world.biome.Biome;

public class BiomeBasic extends Biome {

	public BiomeBasic(BiomeProperties properties)
	{
		super(properties);
	}
	
	public BiomeBasic(String name)
	{
		this(new BiomeProperties(name));
	}
}
