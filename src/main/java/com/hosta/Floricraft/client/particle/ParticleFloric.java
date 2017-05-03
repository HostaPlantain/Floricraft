package com.hosta.Floricraft.client.particle;

import com.hosta.Floricraft.helper.WindHelper;
import com.hosta.Floricraft.init.FloricraftInit;
import com.hosta.Floricraft.item.ItemMetaFlower;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleFloric extends ParticleBasic {

	public ParticleFloric(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, TextureAtlasSprite texture)
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.setMaxAge(1024);
		this.particleGravity = 0.07f;
		this.particleScale = 0.5f;
		this.setParticleTexture(texture);
	}

	public ParticleFloric(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, TextureAtlasSprite texture, int type)
	{
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, type == 1 ? 0.02D : 0.0D, 0.0D, texture);
		
		if (type == 2)
		{
			float value = 2 * (float) Math.PI * rand.nextFloat();
    		this.motionX = MathHelper.cos(value) * 0.01D;
            this.motionY = - (double)this.particleGravity;
    		this.motionZ = MathHelper.sin(value) * 0.01D;
		}
	}
	
	public ParticleFloric(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int meta)
	{
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(FloricraftInit.PETAL_RAW, meta));
	}
	
	public ParticleFloric(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, int meta, int type)
	{
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, type == 1 ? 0.02D : 0.0D, 0.0D, meta);
		
		if (type == 2)
		{
			float value = 2 * (float) Math.PI * rand.nextFloat();
    		this.motionX = MathHelper.cos(value) * 0.01D;
            this.motionY = - (double)this.particleGravity;
    		this.motionZ = MathHelper.sin(value) * 0.01D;
		}
	}
		
	@Override
    public int getFXLayer()
    {
        return 1;
    }

    @Override
    public void onUpdate()
    {
    	this.onGround = this.posY == this.prevPosY && this.particleAge > 10;
    	
    	this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
        
        double[] wind = WindHelper.getWind(this.world);

        IBlockState onBlock = this.world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ));
        if (onBlock.getMaterial().isLiquid())
        {
        	double waterLevel = (double)(1.0F - BlockLiquid.getLiquidHeightPercent(((Integer)onBlock.getValue(BlockLiquid.LEVEL)).intValue()));
        	boolean isAbove = (double)MathHelper.floor(this.posY) + waterLevel + 0.1D < this.posY;
        	boolean isBelow = (double)MathHelper.floor(this.posY) + waterLevel > this.posY || this.world.getBlockState(new BlockPos(this.posX, this.posY + 1, this.posZ)).getMaterial().isLiquid();
        	
        	wind[0] *= isBelow ? 0.0D : 0.1D;
        	wind[1] = 0.0D;
        	wind[2] *= isBelow ? 0.0D : 0.1D;
        	this.motionX = 0.0D;
            this.motionY = isAbove || isBelow ? motionY < 0.0D ? motionY : - (double)this.particleGravity * 0.05D : 0.0D;
            this.motionZ = 0.0D;
        }
        else if (!this.onGround)
        {
        	if (this.particleAge < 10)
	        {
	            this.motionX *= 0.9D;
	            this.motionY *= 0.9D;
	            this.motionZ *= 0.9D;
	        }
	        else if (this.particleAge % 20 == 10)
	        {
	        	float value = 2 * (float) Math.PI * rand.nextFloat();
	    		this.motionX = MathHelper.cos(value) * 0.01D;
	            this.motionY = - (double)this.particleGravity;
	    		this.motionZ = MathHelper.sin(value) * 0.01D;
	        }
	        else if (this.particleAge > 10)
	        {
	            this.motionX *= 1.1D;
	            this.motionY += 0.05D * (double)this.particleGravity;
	            this.motionZ *= 1.1D;
	        }
        }
        else
        {
        	this.motionX *= 0.5D;
            this.motionY = - (double)this.particleGravity;
            this.motionZ *= 0.5D;
            
            if (wind[1] <= 0.0D)
            {
            	wind[0] = 0.0D;
            	wind[2] = 0.0D;
            }
            
            this.particleAge += 4;
        }
        
        this.move(this.motionX + wind[0], this.motionY + wind[1], this.motionZ + wind[2]);
    }
    
    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
    {
    	public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
    	{
    		return new ParticleFloric(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, worldIn.rand.nextInt(ItemMetaFlower.max_meta));
    	}
    }
}
