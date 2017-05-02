package com.hosta.Floricraft.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBasicHorizontal extends BlockBasic{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
    public BlockBasicHorizontal(String name, Material materialIn)
    {
		super(name, materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH));
	}
    
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	if(facing.getHorizontalIndex() == -1)
    	{
        	return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    	}
    	return this.getDefaultState().withProperty(FACING, facing.getOpposite());
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;
        
        switch (meta / 4)
        {
        	default:
        	case 0:
        		enumfacing = EnumFacing.SOUTH;
        		break;
        	case 1:
            	enumfacing = EnumFacing.WEST;
        		break;
	      	case 2:
            	enumfacing = EnumFacing.NORTH;
        		break;
        	case 3:
            	enumfacing = EnumFacing.EAST;
        		break;
        }
        
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
        meta *= 4;
    	return meta;
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}
