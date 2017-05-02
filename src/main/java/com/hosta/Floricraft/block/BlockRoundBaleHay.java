package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.handler.EnumHandler.EnumDamaged;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockRoundBaleHay extends BlockRoundBale {
	
	public BlockRoundBaleHay(String name)
	{
		super(name);
	}
    
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
	}
    
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
        if(!worldIn.isRemote && rand.nextInt(5) == 0)
        {
        	EnumFacing.Axis enumfacing$axis = state.getValue(AXIS);
        	EnumDamaged value = state.getValue(DAMAGE);
        	
        	if(value != EnumDamaged.UNDAMAGED)
        	{
        		int meta = getMetaFromState(state);
        		worldIn.setBlockState(pos, this.getStateFromMeta(++meta));
        	}
        	
        	else if(value == EnumDamaged.UNDAMAGED)
        	{
        		worldIn.setBlockState(pos, FloricraftInit.ROUND_BALE_SILAGE.getDefaultState().withProperty(AXIS, enumfacing$axis).withProperty(DAMAGE, EnumDamaged.UNDAMAGED));
        	}
        }
	}
}
