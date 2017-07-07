package com.hosta.Floricraft.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityHelper {

	public static void antiEntityFrom(Entity entity, Class<?> antiClass, int amplifier)
	{
		entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(16, 4, 16)).forEach(anti -> antiEntity(anti, antiClass, entity, amplifier));
	}
	
	private static void antiEntity(Entity antiEntity, Class<?> antiClass, Entity entity, int amplifier)
	{
		if (antiEntity.getClass() == antiClass)
		{
			antiEntity(antiEntity, antiEntity.posX - entity.posX, antiEntity.posZ - entity.posZ, ((double) ++amplifier) / 2);
		}
	}

	public static <T extends Entity> void antiEntityFrom(World world, BlockPos pos, Class<T> antiClass, boolean antiMobOnly)
	{
		world.getEntitiesWithinAABB(antiClass, world.getBlockState(pos).getBoundingBox(world, pos).offset(pos).expand(8, 2, 8)).forEach(anti -> antiEntity(anti, pos, antiMobOnly));
	}

	private static void antiEntity(Entity antiEntity, BlockPos pos, boolean antiMobOnly)
	{
		if (antiMobOnly)
		{
			if (antiEntity instanceof IMob)
			{
				antiEntity(antiEntity, antiEntity.posX - pos.getX(), antiEntity.posZ - pos.getZ(), 0.2);
			}
		}
		else
		{
			antiEntity(antiEntity, antiEntity.posX - pos.getX(), antiEntity.posZ - pos.getZ(), 0.2);
		}
	}
	
	private static void antiEntity(Entity antiEntity, double x, double z, double amplifier)
	{
		double dis = Math.sqrt((x * x) + (z *z));
		antiEntity.motionX = x * amplifier / dis;
		antiEntity.motionZ = z * amplifier / dis;
		spawnEntityParticle(antiEntity, EnumParticleTypes.VILLAGER_ANGRY);
	}
	
	public static void proEntity(Entity proEntity, Class<?> antiClass, BlockPos pos)
	{
		if (proEntity.getClass() == antiClass)
		{
			double dX = pos.getX() - proEntity.posX;
			double dZ = pos.getZ() - proEntity.posZ;
			double dD = Math.sqrt((dX * dX) + (dZ * dZ));
			if (dD > 2)
			{
				proEntity.motionX = dX / dD / 4;
				proEntity.motionZ = dZ / dD / 4;
			}
		}
	}
	
	public static void spawnEntityParticle(Entity entity, EnumParticleTypes particle)
	{
		double[] pos = randomPosInEntityBoundingBox(entity);
		entity.world.spawnParticle(particle, pos[0], pos[1], pos[2], 0.0D, 0.0D, 0.0D, new int[0]);
	}
	
	public static double[] randomPosInEntityBoundingBox(Entity entity)
	{
		AxisAlignedBB aabb = entity.getEntityBoundingBox();
		return new double[]{aabb.minX + Math.random() * (aabb.maxX - aabb.minX), aabb.minY + (1 + Math.random()) * (aabb.maxY - aabb.minY) / 2, aabb.minZ + Math.random() * (aabb.maxZ - aabb.minZ)};
	}
}
