package com.hosta.Floricraft.world.biome;

import com.hosta.Floricraft.config.ConfigChecker;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BiomeFlowerLand extends BiomeBasicWithPath {

	public IBlockState[] crops = {Blocks.RED_FLOWER.getDefaultState()};
	public IBlockState farmLand = topBlock;
	
	public BiomeFlowerLand(BiomeProperties properties)
	{
		super(properties.setTemperature(0.65F));
		if (!ConfigChecker.getCanSpawn())
		{
			this.spawnableMonsterList.clear();
		}
	}
	
	public BiomeFlowerLand(String name)
	{
		this(new BiomeProperties(name));
	}
	
	@Override
	public float getSpawningChance()
	{
		return 0.0F;
	}
	
	public BiomeFlowerLand setCrops(IBlockState crop)
	{
		crops[0] = crop;
		return this;
	}
	
	public BiomeFlowerLand setCropsbyTypes(int type)
	{
		switch(type)
		{
			default:
				break;
			case 1:
				crops = new IBlockState[4];
				for (int i = 0; i < 4; i++)
				{
					crops[i] = Blocks.RED_FLOWER.getStateFromMeta(i + 4);
				}
		}
		return this;
	}
	
	public void setFarmLand(IBlockState farmLand)
	{
		this.farmLand = farmLand;
	}
}
