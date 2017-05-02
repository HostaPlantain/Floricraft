package com.hosta.Floricraft.world.gen.feature;

import java.util.Random;

import com.hosta.Floricraft.helper.DateHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class WorldGenTreeChristmas extends WorldGenTreeBasic {

	private final IBlockState LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
	private IBlockState leaves;
	private IBlockState leaves2;
	
	private int hight;
	private int range = 0;
	
	public WorldGenTreeChristmas(boolean notify, Random rand, IBlockState leaves, IBlockState leaves2, int hight)
	{
		this(notify, rand, leaves, leaves2);
		this.hight = hight;
	}
	
	public WorldGenTreeChristmas(boolean notify, Random rand, IBlockState leaves, IBlockState leaves2)
	{
		this(notify, rand);
		this.leaves = leaves;
		this.leaves2 = leaves2;
	}
	
	private WorldGenTreeChristmas(boolean notify, Random rand)
	{
		super(notify);

		if (!DateHelper.isChristmas())
		{
			if (rand.nextBoolean())
				this.hight = 7;
			else if (rand.nextBoolean())
			{
				if (rand.nextBoolean())
					this.hight = 8;
				else
					this.hight = 6;
			}
			else if (rand.nextBoolean())
				this.hight = 4;
			else if (rand.nextBoolean())
			{
				this.hight = 11;
				this.range = 1;
			}
			else
			{
				this.hight = 12;
				this.range = 1;
			}
		}
		else
		{
			if (rand.nextBoolean())
			{
				this.hight = 12;
				this.range = 1;
			}
			else if (rand.nextBoolean())
			{
				if (rand.nextBoolean())
					this.hight = 7;
				else
				{
					this.hight = 11;
					this.range = 1;
				}
			}
			else if (rand.nextBoolean())
				this.hight = 8;
			else if (rand.nextBoolean())
				this.hight = 6;
			else
				this.hight = 4;
		}
	}

	@Override
	public boolean canGrow(World worldIn, Random rand, BlockPos position)
	{
		BlockPos posMin = position.add(-this.range, 1, -this.range);
		BlockPos posMax = position.add(this.range, this.hight, this.range);
		
		return areReplaceable(worldIn, posMin, posMax) && position.getY() + this.hight < 255;
	}
	
	@Override
	public void generateTree(World worldIn, Random rand, BlockPos position)
	{
    	for (int i = 0; i < this.hight; i++)
			{
    			this.setBlockAndNotifyAdequately(worldIn, position.up(i), LOG);
			}
    	
    	switch (this.hight)
		  	{
		   		case 4:
		   			setLeaves(worldIn, position.up(1), leaves2, 2, false);
					setLeaves(worldIn, position.up(2), leaves, 1);
					setLeaves(worldIn, position.up(3), leaves, 1, false);
					setLeaves(worldIn, position.up(this.hight), leaves);
					break;
						
		   		case 6:
					setLeaves(worldIn, position.up(1), leaves2, 2, false);
					setLeaves(worldIn, position.up(2), leaves, 1);
					setLeaves(worldIn, position.up(3), leaves2, 2, false);
					setLeaves(worldIn, position.up(4), leaves, 1, false);
					setLeaves(worldIn, position.up(5), leaves2, 2, false);
					setLeaves(worldIn, position.up(6), leaves, 1, false);
					setLeaves(worldIn, position.up(this.hight + 1), leaves);
					break;
						
		   		case 7:
					setLeaves(worldIn, position.up(1), leaves2, 3, true);
					setLeaves(worldIn, position.up(2), leaves2, 2, false);
					setLeaves(worldIn, position.up(3), leaves, 1);
					setLeaves(worldIn, position.up(4), leaves2, 2, false);
					setLeaves(worldIn, position.up(5), leaves, 1);
					setLeaves(worldIn, position.up(6), leaves, 1, false);
					setLeaves(worldIn, position.up(this.hight), leaves);
					break;
						
		   		case 8:
					setLeaves(worldIn, position.up(1), leaves2, 3, true);
					setLeaves(worldIn, position.up(2), leaves2, 2, false);
					setLeaves(worldIn, position.up(3), leaves, 1);
					setLeaves(worldIn, position.up(4), leaves2, 2, false);
					setLeaves(worldIn, position.up(5), leaves, 1);
					setLeaves(worldIn, position.up(6), leaves2, 2, false);
					setLeaves(worldIn, position.up(7), leaves, 1, false);
					setLeaves(worldIn, position.up(this.hight), leaves);
					break;
						
		   		case 11:
		   			for (int i = 0; i < 10; i++)
					{
		   				setLeaves(worldIn, position.up(i), LOG, 1);
					}
					setLeaves(worldIn, position.up(2), leaves2, 4, true);
					setLeaves(worldIn, position.up(3), leaves2, 3, false);
					setLeaves(worldIn, position.up(4), leaves, 2);
					setLeaves(worldIn, position.up(5), leaves2, 4, true);
					setLeaves(worldIn, position.up(6), leaves2, 3, false);
					setLeaves(worldIn, position.up(7), leaves, 2, false);
					setLeaves(worldIn, position.up(8), leaves2, 3, true);
					setLeaves(worldIn, position.up(9), leaves, 2, false);
					setLeaves(worldIn, position.up(10), leaves, 1);
					setLeaves(worldIn, position.up(11), leaves, 1, false);
					setLeaves(worldIn, position.up(this.hight + 1), leaves);
					break;
						
		   		case 12:
		   			for (int i = 0; i < 10; i++)
					{
		   				setLeaves(worldIn, position.up(i), LOG, 1);
					}
					setLeaves(worldIn, position.up(2), leaves2, 4, true);
					setLeaves(worldIn, position.up(3), leaves2, 3, false);
					setLeaves(worldIn, position.up(4), leaves, 3, true);
					setLeaves(worldIn, position.up(5), leaves, 2, false);
					setLeaves(worldIn, position.up(6), leaves2, 3, false);
					setLeaves(worldIn, position.up(7), leaves, 2);
					setLeaves(worldIn, position.up(8), leaves2, 3, true);
					setLeaves(worldIn, position.up(9), leaves, 2, false);
					setLeaves(worldIn, position.up(10), leaves2, 2, true);
					setLeaves(worldIn, position.up(11), leaves, 1);
					setLeaves(worldIn, position.up(12), leaves, 1, false);
					setLeaves(worldIn, position.up(this.hight + 1), leaves);
					break;
		  	}
    	
		if (DateHelper.isChristmas())
		{
			switch (this.hight)
		  	{
	   			case 11:
	   			case 12:
	   				for (int i = 0; i < 4; i++)
					{
		   				int x = rand.nextInt(5) - 2;
		   				int z = rand.nextInt(5) - 2;
		   				
		   				if (x != z && x != -z)
		   				{
		   					BlockPos pos = position.north(x).east(z);
		   					setChest(worldIn, pos, rand);
		   				}
					}
                    break;
		  	}
		}
	}
	
	public void setLeaves(World worldIn, BlockPos posIn, IBlockState state, int range, boolean nonCorner)
	{
		int[] min = new int[]{posIn.getX() - range, posIn.getZ() - range};
		int[] max = new int[]{posIn.getX() + range, posIn.getZ() + range};
		
		for (int i = min[0]; i <= max[0]; i++)
		{
			for (int k = min[1]; k <= max[1]; k++)
			{
				boolean flag = true;
				
				if (nonCorner)
				{
					if (i == min[0]|| i == max[0])
					{
						if (k == min[1]|| k == max[1])
						{
							flag = false;
						}
						else if (k == min[1] + 1|| k == max[1] - 1)
						{
							flag = false;
						}
					}
					
					if (i == min[0] + 1|| i == max[0] - 1)
					{
						if (k == min[1]|| k == max[1])
						{
							flag = false;
						}
					}
				}
				else
				{
					if (i == min[0]|| i == max[0])
					{
						if (k == min[1]|| k == max[1])
						{
							flag = false;
						}
					}
				}
				
				if (flag)
				{
					BlockPos pos = new BlockPos(i, posIn.getY(), k);
					setLeaves(worldIn, pos, state);
				}
			}
		}
	}
	
	public void setLeaves(World worldIn, BlockPos posIn, IBlockState state, int range)
	{
		int[] min = new int[]{posIn.getX() - range, posIn.getZ() - range};
		int[] max = new int[]{posIn.getX() + range, posIn.getZ() + range};
		
		for (int i = min[0]; i <= max[0]; i++)
		{
			for (int k = min[1]; k <= max[1]; k++)
			{
				BlockPos pos = new BlockPos(i, posIn.getY(), k);
				setLeaves(worldIn, pos, state);
			}
		}
	}
	
	public boolean setLeaves(World worldIn, BlockPos pos, IBlockState state)
	{
		if (isReplaceable(worldIn, pos) && worldIn.getBlockState(pos) != LOG)
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, state);
            return true;
        }
		return false;
	}
	
	public void setChest(World worldIn, BlockPos pos, Random rand)
	{
		if (checkChest(worldIn, pos) && setLeaves(worldIn, pos, Blocks.CHEST.correctFacing(worldIn, pos, Blocks.CHEST.getDefaultState())))
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntityChest)
            {
            	TileEntityChest chest = (TileEntityChest)tileEntity;
            	chest.setCustomName("Christmas Present");
            	
            	int i = rand.nextInt(5);
            	switch (i)
            	{
	   				case 0:
	   					chest.setLootTable(LootTableList.CHESTS_SIMPLE_DUNGEON, rand.nextLong());
	   	                break;
	   				case 1:
	   					chest.setLootTable(LootTableList.CHESTS_IGLOO_CHEST, rand.nextLong());
	   	                break;
	   				case 2:
	   					chest.setLootTable(LootTableList.CHESTS_NETHER_BRIDGE, rand.nextLong());
	   	                break;
	   				case 3:
	   					chest.setLootTable(LootTableList.CHESTS_END_CITY_TREASURE, rand.nextLong());
	   	                break;
	   				case 4:
	   					chest.setLootTable(LootTableList.CHESTS_VILLAGE_BLACKSMITH, rand.nextLong());
	   	                break;
            	}
            	
            	for (int j = 0; j < chest.getSizeInventory(); j++)
            	{
            		if (rand.nextBoolean() && rand.nextBoolean())
            		{
	                	int k = rand.nextInt(11);
	                	ItemStack stack = null;
	                	switch (k)
	                	{
	    	   				case 0:
	    	   				case 1:
	    	   				case 2:
	    	   				case 3:
	    	   					stack = new ItemStack(FloricraftInit.ORNAMENT_CHRISTMAS, rand.nextInt(2) + 1, k);
	    	   	                break;
	    	   				case 4:
	    	   				case 5:
	    	   				case 6:
	    	   					if (rand.nextBoolean() && rand.nextBoolean())
	    	   					{
		    	   					stack = new ItemStack(FloricraftInit.SAPLING_CHRISTMAS, 1, k - 4);
		    	   	                break;
	    	   					}
	    	   				case 7:
	    	   				case 8:
	    	   				case 9:
	    	   					stack = new ItemStack(FloricraftInit.CONFECTION_EVENT, rand.nextInt(2) + 1, k - 7);
	    	   	                break;
	    	   				case 10:
	    	   					stack = new ItemStack(Items.CAKE, 1);
	    	   	                break;
	                	}
	                	if (stack != null)
	                	{
	                		chest.setInventorySlotContents(j, stack);
	                	}
            		}
            	}
            }
		}
	}
	
	public boolean checkChest(World worldIn, BlockPos posIn)
	{
		for (int i = 0; i < 4; i++)
		{
			BlockPos pos = null;
			switch (i)
			{
				case 0:
					pos = posIn.north();
					break;
				case 1:
					pos = posIn.south();
					break;
				case 2:
					pos = posIn.east();
					break;
				case 3:
					pos = posIn.west();
					break;
			}
			if (worldIn.getBlockState(pos).getBlock() == Blocks.CHEST)
			{
				return false;
			}
		}
		return true;
	}
}
