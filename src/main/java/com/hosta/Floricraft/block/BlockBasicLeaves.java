package com.hosta.Floricraft.block;

import java.util.List;

import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;
import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBasicLeaves extends BlockLeaves{

    public static final PropertyEnum<EnumVariant> VARIANT = PropertyEnum.create("variant", EnumVariant.class);	
	
    public BlockBasicLeaves(String name)
    {
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariant.TYPE0).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
    {
		for(int i = 0; i < EnumVariant.getMaxMeta(); i++)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		meta |= 4;
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
	}
	
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	return this.getDefaultState().withProperty(VARIANT, EnumVariant.getEnumByMeta(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT, CHECK_DECAY, DECAYABLE});
    }

    @Override
    public int damageDropped(IBlockState state)
    {
    	return state.getValue(VARIANT).getMeta();
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
    	return new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(state));
    }
    
    @Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
    	return new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(state));
	}
    
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
        return java.util.Arrays.asList(new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		return BlockPlanks.EnumType.SPRUCE;
	}
}
