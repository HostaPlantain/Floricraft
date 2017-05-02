package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockOreSalt extends BlockBasic{
	
	public BlockOreSalt(String name, Material materialIn)
	{
		super(name, materialIn);
		this.setHardness(1.5F).setResistance(30.0F).setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return FloricraftInit.DUST_SALT;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
        return 3 + random.nextInt(3);
	}
}
