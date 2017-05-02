package com.hosta.Floricraft.helper;

import com.hosta.Floricraft.config.ConfigChecker;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WindHelper {

	static final int FUNC_NORTH = 8168;
	static final int FUNC_WEST = 10681;
	static final int FUNC_TICK = FUNC_NORTH * FUNC_WEST;
	
	static int worldTick;
	static double windNorth;
	static double windWest;
	static double windDown = 0.0D;

	public static double[] getWind(World worldIn)
	{
		return ConfigChecker.isWindEnabled() ? getWind(getWorldTick(worldIn)) : new double[]{0.0D, 0.0D, 0.0D};
	}
	
	public static double getWindNorth(World worldIn)
	{
		return ConfigChecker.isWindEnabled() ? getWindNorth(getWorldTick(worldIn)) : 0.0D;
	}

	public static double getWindWest(World worldIn)
	{
		return ConfigChecker.isWindEnabled() ? getWindWest(getWorldTick(worldIn)) : 0.0D;
	}

	public static double getAngle(World worldIn)
	{
		return ConfigChecker.isWindEnabled() ? getAngle(getWorldTick(worldIn)) : 0.0D;
	}
	
	private static double[] getWind(int worldTick)
	{
		setWind(worldTick);
		return new double[]{windWest, windDown, windNorth};
	}
	
	private static double getWindNorth(int worldTick)
	{
		setWind(worldTick);
		return windNorth;
	}

	private static double getWindWest(int worldTick)
	{
		setWind(worldTick);
		return windWest;
	}

	private static double getAngle(int worldTick)
	{
		setWind(worldTick);
		return getAngle();
	}
	
	private static double getAngle()
	{
		return WindHelper.windWest <= 0 ? Math.acos(getCos()) : 6.2831853 - Math.acos(getCos());
	}
	
	private static double getCos()
	{
		return windNorth / getRadius();
	}
	
	private static double getRadius()
	{
		return Math.sqrt((windNorth * windNorth) + (windWest * windWest));
	}

	private static int getWorldTick(World worldIn)
	{
        return (int)(worldIn.getTotalWorldTime() % FUNC_TICK);
	}
	
	private static void setWind(int worldTick)
	{
		if (worldTick != WindHelper.worldTick)
		{
			WindHelper.worldTick = worldTick;
			setWindNorth(worldTick);
			setWindWest(worldTick);
		}
	}
	
	private static void setWindNorth(int worldTick)
	{
		WindHelper.windNorth = WindHelper.functionWindNorth(worldTick);
	}

	private static void setWindWest(int worldTick)
	{
		WindHelper.windWest = WindHelper.functionWindWest(worldTick);
	}
	
	private static double functionWindNorth(int x)
	{
		float rareX = ((float)(x % FUNC_NORTH)) / 1300;
		double funcX = MathHelper.cos(rareX) + MathHelper.cos(rareX * 2) + MathHelper.cos(rareX * 5);
		return funcX / 20;
	}

	private static double functionWindWest(int x)
	{
		float rareX = ((float)(x % FUNC_WEST)) / 1700;
		double funcX = MathHelper.cos(rareX) + MathHelper.cos(rareX * 2) + MathHelper.cos(rareX * 5);
		return funcX / 20;
	}
}
