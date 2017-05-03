package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.world.biome.BiomeBasicWithPath;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainGenHandler {

	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event)
	{
		BlockPos pos = new BlockPos(event.getChunkX() * 16 + 8, 0, event.getChunkZ() * 16 + 8);
		Biome biome = event.getWorld().getBiome(pos);
		if (biome instanceof BiomeBasicWithPath)
		{
			if (event.getType() == PopulateChunkEvent.Populate.EventType.ANIMALS || event.getType() == PopulateChunkEvent.Populate.EventType.LAKE || event.getType() == PopulateChunkEvent.Populate.EventType.LAVA)
			{
				event.setResult(Result.DENY);
			}
		}
	}
}
