package com.hosta.Floricraft.item;

import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.init.FloricraftTabs;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemBottleBrown extends ItemGlassBottle {

	public ItemBottleBrown(String name)
	{
		this.setUnlocalizedName(name).setCreativeTab(FloricraftTabs.tabFloricraft);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (raytraceresult == null)
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        }
        else
        {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockpos = raytraceresult.getBlockPos();
                
                if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER)
                {
                    worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, this.turnBottleIntoItem(itemstack, playerIn, new ItemStack(FloricraftInit.BOTTLE_BROWN_WATER)));
                }
            }

            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        }
    }
}
