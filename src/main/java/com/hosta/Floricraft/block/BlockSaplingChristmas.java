package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.world.gen.feature.WorldGenTreeChristmas;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSaplingChristmas extends BlockBasicSapling implements IMetaBlockName{

	public BlockSaplingChristmas(String name)
	{
		super(name);
		this.setLightLevel(0.5F);
	}

	@Override
	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		int i = state.getValue(VARIANT).getMeta();
		WorldGenTreeChristmas generator;
		
		IBlockState leaves1;
		IBlockState leaves2;
		
		switch (i)
		{
			default :
			case 0:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState();
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE1);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
			case 1:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE2);
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE3);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
			case 2:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState();
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE1);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
			case 3:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE2);
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumVariant.TYPE3);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
		}
		
		if (generator != null)
		{
			generator.generate(worldIn, rand, pos);
		}
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumVariant.getSpecialName(stack.getItemDamage() % 4);
	}
}
