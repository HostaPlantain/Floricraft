package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.Floricraft;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLeavesFloric extends BlockBasicLeaves {

	public BlockLeavesFloric(String name)
	{
		super(name);
		this.setLightLevel(0.4f);
	}

	@Override
	protected int getSaplingDropChance(IBlockState state)
	{
		return 200;
	}
	
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
    	
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return Item.getItemFromBlock(FloricraftInit.SAPLING_FLORIC_TYPE0);
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos blockPos, Random rand)
    {
    	super.randomDisplayTick(stateIn, worldIn, blockPos, rand);
    	
    	int flower = ItemMetaFlower.getMetaFromName(createStackedBlock(stateIn).getUnlocalizedName().substring(getUnlocalizedName().length() + 1));
    	if (flower >= 0 && rand.nextInt(3) == 0 && worldIn.isAirBlock(blockPos.down()) && worldIn.isAirBlock(blockPos.down(2)))
    	{
    		double[] pos = new double[] {blockPos.getX() + rand.nextDouble(), blockPos.getY(), blockPos.getZ() + rand.nextDouble()};
        	Floricraft.proxy.spawnFloricParticle(worldIn, pos, flower, 2);
    	}
    }
}
