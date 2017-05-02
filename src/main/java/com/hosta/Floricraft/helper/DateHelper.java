package com.hosta.Floricraft.helper;

import java.time.LocalDateTime;

public class DateHelper {

	public static int getMonthValue()
	{
		return LocalDateTime.now().getMonthValue();
	}
	
	public static int getDayOfMonth()
	{
		return LocalDateTime.now().getDayOfMonth();
	}
	
	public static boolean isDicember()
	{
		return DateHelper.getMonthValue() == 12;
	}
	
	public static boolean isChristmas()
	{
		return DateHelper.getMonthValue() == 12 && (DateHelper.getDayOfMonth() == 24 || DateHelper.getDayOfMonth() == 25);
	}
	
	public static boolean isNewYear()
	{
		return DateHelper.getMonthValue() == 1 && (DateHelper.getDayOfMonth() == 1 || DateHelper.getDayOfMonth() == 2);
	}
	
	public static boolean isAprilFool()
	{
		return DateHelper.getMonthValue() == 4 && DateHelper.getDayOfMonth() == 1;
	}
}
