package com.hosta.Floricraft.block;

import java.util.Random;

import com.hosta.Floricraft.Floricraft;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTorchFloric extends BlockBasicTorch {

	public BlockTorchFloric(String name)
	{
		super(name);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        double [] position;
        
        if (enumfacing.getAxis().isHorizontal())
        {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            //worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
            //worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
            position = new double[]{d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ()};
        }
        else
        {
            //worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            //worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            position = new double[]{d0, d1, d2};
        }
        
        String stack = stateIn.getBlock().getUnlocalizedName().substring(18);
        int flower = ItemMetaFlower.getMetaFromName(stack);
        
		Floricraft.proxy.spawnFloricParticle(worldIn, position, flower, 2);
    }
}
