package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.MinecraftForge;

public class ItemSeedsHemp extends ItemSeeds {
	
	public ItemSeedsHemp(String unlocalizedName, Block crops, Block soil)
	{
		super(crops, soil);
		this.setUnlocalizedName(unlocalizedName).setCreativeTab(FloricraftTabs.tabFloricraft);
		MinecraftForge.addGrassSeed(new ItemStack(this, 1, 0), 20);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stackIn, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        ItemStack stack = playerIn.getHeldItem(hand);
        if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), FloricraftInit.CROP_HEMP.getDefaultState());
            stack.splitStack(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
	{
		return EnumPlantType.Crop;
	}
	
	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos)
	{
		return FloricraftInit.CROP_HEMP.getDefaultState();
	}
	
	
}
