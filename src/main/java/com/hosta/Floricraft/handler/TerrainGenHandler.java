package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.block.BlockBasicLeaves;
import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.world.biome.BiomeBasicWithPath;
import com.hosta.Floricraft.world.gen.feature.WorldGenTreeFloric;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainGenHandler {

	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event)
	{
		BlockPos pos = new BlockPos(event.getChunkX() * 16 + 8, 0, event.getChunkZ() * 16 + 8);
		Biome biome = event.getWorld().getBiomeGenForCoords(pos);
		if (biome instanceof BiomeBasicWithPath)
		{
			if (event.getType() == PopulateChunkEvent.Populate.EventType.ANIMALS || event.getType() == PopulateChunkEvent.Populate.EventType.LAKE || event.getType() == PopulateChunkEvent.Populate.EventType.LAVA)
			{
				event.setResult(Result.DENY);
			}
		}
	}
	
	private static final WorldGenTreeFloric SAKURA = new WorldGenTreeFloric(false, Blocks.LOG.getDefaultState(), FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE0));
	private static final WorldGenTreeFloric DOGWOOD = new WorldGenTreeFloric(false, Blocks.LOG.getDefaultState(), FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE1));
	private static final WorldGenTreeFloric KERRIA = new WorldGenTreeFloric(false, Blocks.LOG.getDefaultState(), FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE2));
	private static final WorldGenTreeFloric AZALEA = new WorldGenTreeFloric(false, Blocks.LOG.getDefaultState(), FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE3));

	@SubscribeEvent
	public void onDecorateBiome(DecorateBiomeEvent.Decorate event)
	{
		if (event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE && event.getRand().nextBoolean())
		{
			int x = event.getRand().nextInt(16) + 8;
			int z = event.getRand().nextInt(16) + 8;
			
			BlockPos pos = event.getPos().getY() != 0 ? event.getPos().add(x, 0, z) : event.getWorld().getTopSolidOrLiquidBlock(event.getPos().add(x, 0, z));
			Biome biome = event.getWorld().getBiomeForCoordsBody(pos);
			
			if (biome == Biomes.MUTATED_FOREST)
			{
				switch (event.getRand().nextInt(8))
				{
					case 0:
						SAKURA.generate(event.getWorld(), event.getRand(), pos);
						break;
					case 1:
						DOGWOOD.generate(event.getWorld(), event.getRand(), pos);
						break;
					case 2:
						KERRIA.generate(event.getWorld(), event.getRand(), pos);
						break;
					case 3:
						AZALEA.generate(event.getWorld(), event.getRand(), pos);
						break;
				}
			}
		}
	}
}
