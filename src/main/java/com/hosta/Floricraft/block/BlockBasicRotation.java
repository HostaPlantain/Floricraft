package com.hosta.Floricraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class BlockBasicRotation extends BlockBasicContainer{
	
    public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

	public BlockBasicRotation(String name, Material materialIn)
	{
		super(name, materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, Integer.valueOf(0)));
	}
    
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		int i = MathHelper.floor((double)((placer.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
    	return this.getDefaultState().withProperty(ROTATION, Integer.valueOf(i));
	}
	
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(ROTATION, Integer.valueOf(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(ROTATION);
    }
    
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(ROTATION, Integer.valueOf(rot.rotate(((Integer)state.getValue(ROTATION)).intValue(), 16)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(ROTATION, Integer.valueOf(mirrorIn.mirrorRotation(((Integer)state.getValue(ROTATION)).intValue(), 16)));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ROTATION});
    }
}
