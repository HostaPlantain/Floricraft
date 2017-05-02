package com.hosta.Floricraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class FloricraftAchievement {
	
	public static AchievementPage floricraft;
	private static Achievement[] floricraftAchievements;
	
	public static Achievement red_flower;
	
	public static void init()
	{
		red_flower = new Achievement("achievement.red_flower", "red_flower", 0, 0, Blocks.RED_FLOWER, null);
		
		floricraftAchievements = new Achievement[]{red_flower};
		floricraft = new AchievementPage("achievementPage.floricraft", floricraftAchievements);
	}

	public static void registers()
	{
		AchievementPage.registerAchievementPage(floricraft);
	}
}
