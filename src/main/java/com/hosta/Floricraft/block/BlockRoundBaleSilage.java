package com.hosta.Floricraft.block;

import java.util.List;
import java.util.Random;

import com.hosta.Floricraft.handler.EnumHandler.EnumDamaged;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRoundBaleSilage extends BlockRoundBale implements IMetaBlockName{
	
	public BlockRoundBaleSilage(String name)
	{
		super(name);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < 4; i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(DAMAGE).getMeta();
    }
	
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(state));
    }
    
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1,  damageDropped(state));
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
        if(!worldIn.isRemote)
        {
    		AxisAlignedBB aabb = state.getBoundingBox(worldIn, pos);
    		aabb = aabb.offset(pos).expand(8, 4, 8);
    		List<EntityAnimal> list = worldIn.getEntitiesWithinAABB(EntityAnimal.class, aabb);
    		
    		list.forEach(animal -> animal.addPotionEffect(new PotionEffect(FloricraftInit.POTION_TEMPTED, 200, 0, true, false)));
    		
    		if(rand.nextInt(10) == 0)
    		{
    			EnumDamaged value = state.getValue(DAMAGE);
    			
    			if(value != EnumDamaged.DAMAGED2)
    			{
    				int meta = getMetaFromState(state);
    				worldIn.setBlockState(pos, this.getStateFromMeta(--meta));
    			}
    			else if(value == EnumDamaged.DAMAGED2)
        		{
    				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        		}
    		}
        }
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumDamaged.getSpecialName(stack.getItemDamage() % 4);
	}
}
