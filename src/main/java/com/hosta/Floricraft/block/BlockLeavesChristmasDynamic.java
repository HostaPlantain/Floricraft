package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.helper.DateHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeavesChristmasDynamic extends BlockLeavesChristmas {

	public BlockLeavesChristmasDynamic(String name, float lightLevel)
	{
		super(name, lightLevel);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
	{
        list.add(new ItemStack(itemIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);
		
		if (!worldIn.isRemote)
		{
			updateDynamicLeaves(worldIn, pos, state, rand);
		}
	}

	public void updateDynamicLeaves(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			Block block = state.getBlock();
			int meta = ((BlockBasicLeaves)block).getMetaFromState(state);
			
			IBlockState newState = null;
			
			if (block == FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC && worldIn.getWorldTime() % 24000 <= 12000)
			{
				newState = ((BlockBasicLeaves)FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT).getStateFromMeta(meta);
			}
			else if (DateHelper.isDicember())
			{
				if (block == FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT && worldIn.getWorldTime() % 24000 >= 12000)
				{
					newState = ((BlockBasicLeaves)FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC).getStateFromMeta(meta);
				}
				else if (block == FloricraftInit.LEAVES_CHRISTMAS_UNLIT)
				{
					newState = ((BlockBasicLeaves)FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT).getStateFromMeta(meta);
				}
			}
			else if (block == FloricraftInit.LEAVES_CHRISTMAS_DYNAMIC_UNLIT)
			{
				newState = ((BlockBasicLeaves)FloricraftInit.LEAVES_CHRISTMAS_UNLIT).getStateFromMeta(meta);
			}
			
			if (newState != null)
			{
				worldIn.setBlockState(pos, newState, 2);
			}
		}
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
    	return new ItemStack(Item.getItemFromBlock(this));
	}
}
