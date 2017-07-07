package com.hosta.Floricraft.entity;

import com.hosta.Floricraft.helper.WindHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBallon extends EntityBasic {

    private static final DataParameter<Byte> COLOR = EntityDataManager.<Byte>createKey(EntityBallon.class, DataSerializers.BYTE);
	
	public EntityBallon(World worldIn, byte color, double x, double y, double z)
    {
        this(worldIn);
        
        setColor(color);
        
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

	public EntityBallon(World worldIn)
	{
		super(worldIn);
        this.setSize(0.6F, 0.6F);
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(COLOR, (byte)0);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		this.setDead();
		return true;
	}

	@Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

	@Override
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.getEntityBoundingBox();
    }
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}
	
	@Override
	public void onUpdate()
	{
		double x = WindHelper.getWindWest(this.world);
		double y = 0.05D;
		double z = WindHelper.getWindNorth(this.world);
		
		move(MoverType.SELF, x, y, z);
		
		if (posY >= 256 && this.world.rand.nextInt(4) == 1)
		{
			this.setDead();
		}
		
		super.onUpdate();
	}

	public byte getColor()
	{
		return this.dataManager.get(COLOR);
	}

	public void setColor(byte color)
	{
		this.dataManager.set(COLOR, color);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
        compound.setByte("Color", getColor());
        
        super.writeEntityToNBT(compound);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		if (compound.hasKey("Color"))
        {
			setColor(compound.getByte("Color"));
        }
		
        super.readEntityFromNBT(compound);
	}
	
	@Override
	public boolean canBePushed()
	{
		return true;
	}
}
