package com.hosta.Floricraft.block;

import com.hosta.Floricraft.handler.EnumHandler.EnumVariant;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWreath extends BlockStack implements IMetaBlockName{

	protected static final AxisAlignedBB AABB[] =new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D), new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    public static final PropertyEnum<EnumVariant> VARIANT = PropertyEnum.create("variant", EnumVariant.class);	
    
	public BlockWreath(String name)
    {
		super(name, Material.LEAVES);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(VARIANT, EnumVariant.TYPE0));
	}
    
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < EnumVariant.getMaxMeta(); i++)
        {
			items.add(new ItemStack(item, 1, i));
        }
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(VARIANT, EnumVariant.getEnumByMeta(meta));
	}

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	return super.getStateFromMeta(meta).withProperty(VARIANT, EnumVariant.getEnumByMeta(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return super.getMetaFromState(state) + state.getValue(VARIANT).getMeta();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, VARIANT});
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
	public String getSpecialName(ItemStack stack)
	{
		return EnumVariant.getSpecialName(stack.getItemDamage() % EnumVariant.getMaxMeta());
	}
}
