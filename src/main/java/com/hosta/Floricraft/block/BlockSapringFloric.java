package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;
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
	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		IBlockState log = null;
		IBlockState leaves = null;
		switch (ItemBlockMetaWood.getSpecialName(state))
		{
			default :
			case "sakura":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE0);
				break;
			case "dogwood":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE1);
				break;
			case "kerria":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE2);
				break;
			case "azalea":
				log = Blocks.LOG.getDefaultState();
				leaves = FloricraftInit.LEAVES_FLORIC_TYPE0.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE3);
				break;
		}
		new WorldGenTreeFloric(true, log, leaves).generate(worldIn, rand, pos);
	}
}
