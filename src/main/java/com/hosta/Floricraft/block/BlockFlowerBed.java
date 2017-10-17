package com.hosta.Floricraft.block;

import javax.annotation.Nullable;

import com.hosta.Floricraft.tileentity.TileEntityFlowerBed;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFlowerBed extends BlockBasicContainer {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0, 0.0D, 0.0, 1.0D, 0.125D, 1.0D);
	
	public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    
	public BlockFlowerBed(String name)
	{
		super(name, Material.CIRCUITS);
		this.setHardness(0.0F).setResistance(0.0F).setLightOpacity(0);
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
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate)
	{
		TileEntityFlowerBed planter = getTileEntityPlanter(world, pos);
		if (planter != null)
    	{
			ItemStack[] items = planter.getDisplayedItems();
			for (int i = 0; i < 3; i++)
			{
				if (items[i] != null && !items[i].isEmpty())
	    		{
	    			world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), items[i]));
	    		}
			}
    	}
	    super.breakBlock(world, pos, blockstate);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	TileEntityFlowerBed planter = getTileEntityPlanter(worldIn, pos);
    	if(planter != null)
        {
    		planter.onClick(playerIn, playerIn.getHeldItem(hand), hand);
            return true;
        }
        return false;
    }
    
	@Nullable
    public TileEntityFlowerBed getTileEntityPlanter(IBlockAccess world, BlockPos pos)
    {
    	TileEntity te = world.getTileEntity(pos);
    	if(te != null && te instanceof TileEntityFlowerBed)
    	{
    		TileEntityFlowerBed planter = (TileEntityFlowerBed)te;
    		return planter;
    	}
		return null;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return state.withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north()))).withProperty(EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east()))).withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south()))).withProperty(WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west())));
	}
	
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() instanceof BlockFlowerBed;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		switch (rot)
		{
			case CLOCKWISE_180:
				return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
			case COUNTERCLOCKWISE_90:
				return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
			case CLOCKWISE_90:
				return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
			default:
				return state;
		}
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		switch (mirrorIn)
		{
			case LEFT_RIGHT:
				return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
			case FRONT_BACK:
				return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
			default:
				return state;
		}
	}
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFlowerBed();
	}
}
