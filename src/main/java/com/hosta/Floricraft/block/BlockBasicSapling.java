package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;

import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockBasicSapling extends BlockBasicBush implements IGrowable{

    public static final PropertyEnum<EnumVariant> VARIANT = PropertyEnum.create("variant", EnumVariant.class);	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	
	public BlockBasicSapling(String name)
	{
		super(name);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }

	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
		for(int i = 0; i < EnumVariant.getMaxMeta(); i++)
        {
			list.add(new ItemStack(this, 1, i));
        }
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumVariant.getEnumByMeta(meta & 7)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();
        i = i | ((Integer)state.getValue(STAGE)).intValue() << 3;
        return i;
    }

	@Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT, STAGE});
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
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }
	
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, pos, state, rand);
        }
    }
	
	public abstract void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand);
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

	@Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }
}
