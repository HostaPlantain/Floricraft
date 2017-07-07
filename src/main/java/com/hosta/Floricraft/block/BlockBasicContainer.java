package com.hosta.Floricraft.block;

import com.hosta.Floricraft.init.FloricraftTabs;
import com.hosta.Floricraft.tileentity.TileEntityBasic;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockBasicContainer extends BlockContainer {

	protected BlockBasicContainer(String name,Material materialIn)
	{
		super(materialIn);
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag", 10))
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity != null && tileentity instanceof TileEntityBasic)
            {
                NBTTagCompound nbttagcompound = tileentity.writeToNBT(new NBTTagCompound());
                NBTTagCompound nbttagcompound1 = nbttagcompound.copy();
                NBTTagCompound nbttagcompound2 = (NBTTagCompound)stack.getTagCompound().getTag("BlockEntityTag");
                nbttagcompound.merge(nbttagcompound2);
                nbttagcompound.setInteger("x", pos.getX());
                nbttagcompound.setInteger("y", pos.getY());
                nbttagcompound.setInteger("z", pos.getZ());

                if (!nbttagcompound.equals(nbttagcompound1))
                {
                    tileentity.readFromNBT(nbttagcompound);
                    tileentity.markDirty();
                }
            }
        }
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
}
