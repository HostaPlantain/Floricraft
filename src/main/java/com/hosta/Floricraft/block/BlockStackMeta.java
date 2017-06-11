package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.Floricraft;
import com.hosta.Floricraft.handler.EnumHandler.EnumDrying;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.block.material.Material;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStackMeta extends BlockStack implements IMetaBlockName{
	
	public static final PropertyEnum<EnumDrying> DRYING = PropertyEnum.create("variant", EnumDrying.class);	
    
	public BlockStackMeta(String name, Material materialIn)
    {
		super(name, materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(DRYING, EnumDrying.RAW0));
        this.setTickRandomly(true);
    }
    
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 3));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(DRYING, EnumDrying.getEnumByMeta(meta));
	}

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	return super.getStateFromMeta(meta).withProperty(DRYING, EnumDrying.getEnumByMeta(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return super.getMetaFromState(state) + state.getValue(DRYING).getMeta();
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, DRYING});
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
    	return state.getValue(DRYING).getMeta() < 3 ? 0 : 3;
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
        if(!worldIn.isRemote)
        {
    		int meta = getMetaFromState(state);
    		if((meta % 4) < 3)
        	{
    			if(worldIn.getLightFromNeighbors(pos.up()) > 10 || worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2)
    			{
    				worldIn.setBlockState(pos, FloricraftInit.STACK_DEAD.getDefaultState().withProperty(FACING, state.getValue(FACING)));
    			}
    			else
    			{
    				++meta;
        			worldIn.setBlockState(pos, this.getStateFromMeta(meta));
    			}
        	}
        }
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	if (rand.nextInt(5) == 0 && getMetaFromState(stateIn) % 4 < 3)
    	{
	        EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
	        double d0 = (double)pos.getX() + 0.5D;
	        double d1 = (double)pos.getY() + 0.5D;
	        double d2 = (double)pos.getZ() + 0.5D;
	        double [] position = new double[]{d0 + 0.38D * (double)enumfacing.getFrontOffsetX(), d1 - 0.22D, d2 + 0.38D * (double)enumfacing.getFrontOffsetZ()};;
	        
	        String stack = stateIn.getBlock().getUnlocalizedName().substring(11);
	        int flower = ItemMetaFlower.getMetaFromName(stack);
	        
			Floricraft.proxy.spawnFloricParticle(worldIn, position, flower, 2);
    	}
    }

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumDrying.getSpecialName(stack.getItemDamage() % EnumDrying.getMaxMeta());
	}
}
