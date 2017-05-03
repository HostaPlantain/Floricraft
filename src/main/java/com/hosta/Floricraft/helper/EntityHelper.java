package com.hosta.Floricraft.helper;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class EntityHelper {
	
	public static void antiEntityFrom(Entity entity, Class<?> antiClass, int amplifier)
	{
		entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(16, 4, 16)).forEach(anti -> antiEntity(anti, antiClass, entity, amplifier));
	}
	
	public static void antiEntity(Entity antiEntity, Class<?> antiClass, Entity entity, int amplifier)
	{
		if (antiEntity.getClass() == antiClass)
		{
			amplifier++;
			double dis = antiEntity.getDistanceSqToEntity(entity);
			antiEntity.motionX = (antiEntity.posX - entity.posX) * amplifier * 4 / dis;
			antiEntity.motionZ = (antiEntity.posZ - entity.posZ) * amplifier * 4 / dis;
			spawnEntityParticle(antiEntity, EnumParticleTypes.VILLAGER_ANGRY);
		}
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
