package com.hosta.Floricraft.block;

import com.hosta.Floricraft.handler.EnumHandler.EnumWeatherCock;
import com.hosta.Floricraft.tileentity.TileEntityWeatherCock;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWeatherCock extends BlockBasicContainer implements IMetaBlockName {

	public static final PropertyEnum<EnumWeatherCock> WEATHER_COCK = PropertyEnum.create("cock", EnumWeatherCock.class);
	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);

	public BlockWeatherCock(String name)
	{
		super(name, Material.CIRCUITS);
		this.setHardness(0.5F).setResistance(0.0F).setLightOpacity(0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(WEATHER_COCK, EnumWeatherCock.COCK));
	}

	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < EnumWeatherCock.getMaxMeta(); i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public IBlockState getStateFromMeta(int meta)
	{
		return super.getStateFromMeta(meta).withProperty(WEATHER_COCK, EnumWeatherCock.getEnumByMeta(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(WEATHER_COCK).getMeta();
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
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.CUTOUT;
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {WEATHER_COCK});
	}
	
    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }
	
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(state));
    }
    
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumWeatherCock.getSpecialName(stack.getItemDamage() % EnumWeatherCock.getMaxMeta());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityWeatherCock();
	}
}
