package com.hosta.Floricraft.config;


import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigChecker {

	//Config
	private static Boolean hillStepEnabled;	
	private static Boolean windEnabled;	
	private static int genBiomeWeight;	
	private static Boolean genBiomeFast;	
	private static Boolean canSpawn;
	private static Boolean genSalt;
	
	public static void lordConfig(File file)
	{
		Configuration config = new Configuration(file);
		
		try
		{
			config.load();
			hillStepEnabled = config.getBoolean("hillStepEnabled", "Play", true, "Is Hill Step Enabled By Floric Effect?");
			windEnabled = config.getBoolean("windEnabled", "World", true, "Is Wind Enabled?");
			genBiomeWeight = config.getInt("genBiomeWeight", "World", 10, 0, 1000, "Weight for Flower Land Biome Gen");
			genBiomeFast = config.getBoolean("genBiomeFast", "World", false, "Generate Flower Land Biome Faster? true is NOT Recommended"/*", but it will support RTG"*/);
			canSpawn = config.getBoolean("canSpawn", "World", false, "Can Hostile Mobs Spawn In Flower Lands?");
			genSalt = config.getBoolean("genSalt", "World", true, "Generate Salt Ore?");
        }
		finally
		{
        	config.save();
        }
	}

	public static Boolean isHillStepEnabled()
	{
		return hillStepEnabled;
	}
	
	public static Boolean isWindEnabled()
	{
		return windEnabled;
	}
	
	public static int getGenBiomeWeight()
	{
		return genBiomeWeight;
	}

	public static Boolean getGenBiomeFast()
	{
		return genBiomeFast;
	}
	
	public static Boolean getCanSpawn()
	{
		return canSpawn;
	}
	
	public static Boolean getGenSalt()
	{
		return genSalt;
	}
}
