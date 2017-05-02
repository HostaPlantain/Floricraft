package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemBlockMetaWood;
import com.hosta.Floricraft.world.gen.feature.WorldGenTreeFloric;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSapringFloric extends BlockBasicSapling {

	public BlockSapringFloric(String name)
	{
		super(name);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		IBlockState log = null;
		IBlockState leaves = null;
		switch (ItemBlockMetaWood.getSpecialName(state))
		{
			default :
			case "sakura":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getStateFromMeta(0);
				break;
			case "dogwood":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getStateFromMeta(1);
				break;
			case "kerria":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getStateFromMeta(2);
				break;
			case "azalea":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getStateFromMeta(3);
				break;
		}
		new WorldGenTreeFloric(true, rand, log, leaves).generate(worldIn, rand, pos);
	}
}
