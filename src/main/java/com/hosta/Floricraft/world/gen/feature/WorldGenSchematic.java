package com.hosta.Floricraft.world.gen.feature;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import com.hosta.Floricraft.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSchematic extends WorldGenerator{

	private final HashMap<BlockPos, Integer> MAP_POS = new HashMap<BlockPos, Integer>();
	private final HashMap<BlockPos, NBTTagCompound> MAP_NBT = new HashMap<BlockPos, NBTTagCompound>();
	private final HashMap<Integer, IBlockState> MAP_STATE = new HashMap<Integer, IBlockState>();
	
	public WorldGenSchematic(String name)
	{
		super(true);
		InputStream stream = MinecraftServer.class.getResourceAsStream("/assets/" + Reference.MOD_ID + "/structures/" + name + ".nbt");
		NBTTagCompound nbt = initNBT(stream);
		setMapPos(nbt);
		setMapState(nbt);
	}
	
	private NBTTagCompound initNBT(InputStream stream)
	{
		try
        {
			return CompressedStreamTools.readCompressed(stream);
		}
        catch (IOException e) 
        {
			e.printStackTrace();
		}
		
		return new NBTTagCompound();
	}
	
	private void setMapPos(NBTTagCompound nbt)
	{
		NBTTagList size = (NBTTagList) nbt.getTag("size");
		int sizeX = size.getIntAt(0);
		int sizeY = size.getIntAt(1);
		int sizeZ = size.getIntAt(2);
		
		NBTTagList blocks = (NBTTagList)nbt.getTag("blocks");
		
		int i = 0;
		
		for (int z = 0; z < sizeX; z++)
		{
			for (int x = 0; x < sizeY; x++)
			{
				for (int y = 0; y < sizeZ; y++)
				{
					NBTTagCompound block = blocks.getCompoundTagAt(i++);
					
					NBTTagList pos = (NBTTagList) block.getTag("pos");
					BlockPos blockPos = new BlockPos(pos.getIntAt(0), pos.getIntAt(1), pos.getIntAt(2));
					
					int state = block.getInteger("state");
					
					MAP_POS.put(blockPos, state);
					
					if (block.hasKey("nbt"))
					{
						NBTTagCompound nbtTag = block.getCompoundTag("nbt");
						MAP_NBT.put(blockPos, nbtTag);
					}
				}
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T extends Comparable<T>, V extends T> void setMapState(NBTTagCompound nbt)
	{
		NBTTagList palette = (NBTTagList) nbt.getTag("palette");
		
		for (int i = 0; i < palette.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound) palette.get(i);
			String[] name = tag.getString("Name").split(":");
			
			Block block = Block.REGISTRY.getObject(new ResourceLocation(name[0], name[1]));
			IBlockState state = block.getDefaultState();
			
			if (tag.hasKey("Properties"))
			{
				NBTTagCompound properties = tag.getCompoundTag("Properties");
				for (IProperty property : state.getProperties().keySet())
				{
					state = state.withProperty(property, (V) property.parseValue(properties.getString(property.getName())).get());
				}
			}
			
			MAP_STATE.put(i, state);
		}
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos posStart)
	{
		MAP_POS.forEach((pos, state) -> setBlock(worldIn, pos.add(posStart), MAP_STATE.get(state)));
		MAP_NBT.forEach((pos, nbt) -> setNBT(worldIn.getTileEntity(pos.add(posStart)), nbt));
		
		return true;
	}

	private void setBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (state.getBlock() == Blocks.AIR)
		{
			return;
		}
		else if (state.getBlock() == Blocks.field_189881_dj)
		{
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
		}
		else
		{
			worldIn.setBlockState(pos, state, 2);
		}
	}
	
	private void setNBT(TileEntity te, NBTTagCompound nbt)
	{
		if (te != null && nbt != null)
		{
			nbt.setInteger("x", te.getPos().getX());
			nbt.setInteger("y", te.getPos().getY());
			nbt.setInteger("z", te.getPos().getZ());
			
			te.readFromNBT(nbt);
		}
	}
}
