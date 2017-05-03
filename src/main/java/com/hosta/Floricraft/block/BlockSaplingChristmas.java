package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.Reference;
import com.hosta.Floricraft.handler.EnumHandler;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.init.Registerer;
import com.hosta.Floricraft.world.gen.feature.WorldGenTreeChristmas;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
		int i = ((EnumHandler.EnumVariant)state.getValue(VARIANT)).getMeta();
		WorldGenTreeChristmas generator;
		
		IBlockState leaves1;
		IBlockState leaves2;
		
		switch (i)
		{
			default :
			case 0:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState();
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumHandler.EnumVariant.TYPE1);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
			case 1:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumHandler.EnumVariant.TYPE2);
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumHandler.EnumVariant.TYPE3);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
			case 2:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState();
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumHandler.EnumVariant.TYPE1);
				generator = new WorldGenTreeChristmas(true, rand, leaves1, leaves2);
				break;
			case 3:
				leaves1 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumHandler.EnumVariant.TYPE2);
				leaves2 = FloricraftInit.LEAVES_CHRISTMAS_UNLIT.getDefaultState().withProperty(BlockBasicLeaves.VARIANT, EnumHandler.EnumVariant.TYPE3);
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
		int i = stack.getItemDamage() % 4;
		if(i == 0)
		{
			return "type0";
		}
		else if(i == 1)
		{
			return "type1";
		}
		else if(i == 2)
		{
			return "type2";
		}
		else if(i == 3)
		{
			return "type3";
		}
		return "void";
	}
	
	public static void preRegisterRender(Block block)
	{
		for(int i = 0; i < 16 ; i++)
		{
			int meta = i % 4;
			
			switch (meta)
			{
			default:
			case(0):
				Registerer.registerRender(block, i, block.getUnlocalizedName().substring(5) + "_" + "type0");
				break;
			case(1):
				Registerer.registerRender(block, i, block.getUnlocalizedName().substring(5) + "_" + "type1");
				break;
			case(2):
				Registerer.registerRender(block, i, block.getUnlocalizedName().substring(5) + "_" + "type2");
				break;
			case(3):
				Registerer.registerRender(block, i, block.getUnlocalizedName().substring(5) + "_" + "type3");
				break;
			}
		}
	}
	
	public static void preRegisteryModelBakeryStuff(Block block)
	{
		ModelBakery.registerItemVariants
		(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + "type0"),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + "type1"),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + "type2"),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + "type3")
		);
	}
}
