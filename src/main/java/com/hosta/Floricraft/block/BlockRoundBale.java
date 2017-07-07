package com.hosta.Floricraft.block;

import com.hosta.Floricraft.handler.EnumHandler.EnumDamaged;
import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.BlockHay;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRoundBale extends BlockHay{
	
	public static final PropertyEnum<EnumDamaged> DAMAGE = PropertyEnum.create("damaged", EnumDamaged.class);
    protected static final AxisAlignedBB[] BALE_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D), new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 0.75D, 0.875D), new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 0.75D, 1.0D)};
	
	public BlockRoundBale(String name)
	{
		super();
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(DAMAGE, EnumDamaged.DAMAGED2));
		this.setTickRandomly(true);
	}
	
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
    	return false;
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return super.getStateFromMeta(meta).withProperty(DAMAGE, EnumDamaged.getEnumByMeta(meta % 4));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return super.getMetaFromState(state) + state.getValue(DAMAGE).getMeta();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		int i = super.getMetaFromState(state);
		return BALE_AABB[i < 12 ? i / 4 : 0];
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {AXIS, DAMAGE});
	}
}
