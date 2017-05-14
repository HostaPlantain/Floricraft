package com.hosta.Floricraft.tileentity;

import com.hosta.Floricraft.helper.EntityHelper;
import com.hosta.Floricraft.init.FloricraftInit;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityPotPourri extends TileEntityInventoryWithRender implements ITickable {

    public TileEntityPotPourri()
    {
		super(9, 4);
	}

	private long tick = 0;

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		tick = compound.getLong("Tick");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setLong("Tick", tick);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update()
	{
		tick = items[0] != null ? tick + 1 : 0;
		if (items[0] != null)
		{
			++tick;
			EntityHelper.antiEntityFrom(this.worldObj, this.pos, EntityMob.class);
		
			if (tick % 6000 == 0)
			{
				items[0].splitStack(1);
				if (items[0].stackSize == 0)
				{
					items[0] = null;
					this.setMoveOverItems(0);
				}
				this.markDirty();
			}
		}
	}

	public boolean isWhiteList(Item item)
    {
    	return item == FloricraftInit.PETALS_SALTY || item == FloricraftInit.PETALS_DRY;
    }
}
