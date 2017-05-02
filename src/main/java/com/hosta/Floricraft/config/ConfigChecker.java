package com.hosta.Floricraft.config;


import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigChecker {

	//Config
	private static Boolean hillStepEnabled;	
	private static Boolean windEnabled;	
	private static int genBiomeWeight;	
	
	public static void lordConfig(File file)
	{
		Configuration config = new Configuration(file);
		
		try
		{
			config.load();
			hillStepEnabled = config.getBoolean("hillStepEnabled", "Play", true, "Is Hill Step Enabled By Floric Effect?");
			windEnabled = config.getBoolean("windEnabled", "World", true, "Is Wind Enabled?");
			genBiomeWeight = config.getInt("genBiomeWeight", "World", 10, 0, 1000, "Weight for Flower Land Biome Gen");
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
}
