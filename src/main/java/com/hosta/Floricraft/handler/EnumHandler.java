package com.hosta.Floricraft.handler;

import com.hosta.Floricraft.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public class EnumHandler {
	
	public interface IEnumHandler extends IStringSerializable
	{
		public int getMeta();
		public String getName();
	}

	private static <E extends IEnumHandler> E getIEnumByMeta(E[] iEnum, int meta)
	{
		return iEnum[meta <= 0 ? 0 : (meta >= iEnum.length ? meta % iEnum.length : meta)];
	}

	public enum EnumDrying implements IEnumHandler
	{
		RAW0(0, "raw"),
		RAW1(1, "raw1"),
		RAW2(2, "raw2"),
		DRY0(3, "dry");
		
		private static final EnumDrying[] META_LOOKUP = new EnumDrying[values().length];
		private int meta;
		private String name;
		
		private EnumDrying(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		@Override
		public int getMeta()	{return meta;}
		
		@Override
		public String getName()	{return name;}
		
		@Override
		public String toString(){return getName();}

		public static EnumDrying getEnumByMeta(int meta)
	    {
	        return getIEnumByMeta(META_LOOKUP, meta);
	    }
 
		public static int getMaxMeta()
		{
			return META_LOOKUP.length;
		}

		public static String getSpecialName(int meta)
	    {
	        return getEnumByMeta(meta).getName();
	    }
		
		public static void preRegisteryModelBakeryStuff(Block block)
		{
			ModelBakery.registerItemVariants
			(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(0)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(3))
			);
		}

		static
		{
			for (EnumDrying enumDrying : values())
			{
				META_LOOKUP[enumDrying.getMeta()] = enumDrying;
			}
		}
	}
	
	public enum EnumWeatherCock implements IEnumHandler
	{
		COCK(0, "cock"),
		DOG(1, "dog");
		
	    private static final EnumWeatherCock[] META_LOOKUP = new EnumWeatherCock[values().length];
		private int meta;
		private String name;
		
		private EnumWeatherCock(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		@Override
		public int getMeta()	{return meta;}
		
		@Override
		public String getName()	{return name;}
		
		@Override
		public String toString(){return getName();}

		public static EnumWeatherCock getEnumByMeta(int meta)
	    {
	        return getIEnumByMeta(META_LOOKUP, meta);
	    }

		public static int getMaxMeta()
		{
			return META_LOOKUP.length;
		}

		public static String getSpecialName(int meta)
	    {
	        return getEnumByMeta(meta).getName();
	    }
		
		public static void preRegisteryModelBakeryStuff(Block block)
		{
			ModelBakery.registerItemVariants
			(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(0)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(1))
			);
		}

		static
		{
			for (EnumWeatherCock enumWeatherCock : values())
			{
				META_LOOKUP[enumWeatherCock.getMeta()] = enumWeatherCock;
			}
		}
	}
	
	public enum EnumVariant implements IEnumHandler
	{
		TYPE0(0, "type0"),
		TYPE1(1, "type1"),
		TYPE2(2, "type2"),
		TYPE3(3, "type3");
		
	    private static final EnumVariant[] META_LOOKUP = new EnumVariant[values().length];
		private int meta;
		private String name;
		
		private EnumVariant(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		@Override
		public int getMeta()	{return meta;}
		
		@Override
		public String getName()	{return name;}
		
		@Override
		public String toString(){return getName();}

		public static EnumVariant getEnumByMeta(int meta)
	    {
	        return getIEnumByMeta(META_LOOKUP, meta);
	    }

		public static int getMaxMeta()
		{
			return META_LOOKUP.length;
		}

		public static String getSpecialName(int meta)
	    {
	        return getEnumByMeta(meta).getName();
	    }
		
		public static void preRegisteryModelBakeryStuff(Block block)
		{
			ModelBakery.registerItemVariants
			(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(0)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(1)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(2)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(3))
			);
		}

		static
		{
			for (EnumVariant enumVariant : values())
			{
				META_LOOKUP[enumVariant.getMeta()] = enumVariant;
			}
		}
	}

	public enum EnumDamaged implements IEnumHandler
	{
		DAMAGED2(0, "very_damaged"),
		DAMAGED1(1, "damaged"),
		DAMAGED0(2, "slightly_damaged"),
		UNDAMAGED(3, "undamaged");
		
	    private static final EnumDamaged[] META_LOOKUP = new EnumDamaged[values().length];
		private int meta;
		private String name;
		
		private EnumDamaged(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		@Override
		public int getMeta()	{return meta;}
		
		@Override
		public String getName()	{return name;}
		
		@Override
		public String toString(){return getName();}

		public static EnumDamaged getEnumByMeta(int meta)
	    {
	        return getIEnumByMeta(META_LOOKUP, meta);
	    }

		public static int getMaxMeta()
		{
			return META_LOOKUP.length;
		}

		public static String getSpecialName(int meta)
	    {
	        return getEnumByMeta(meta).getName();
	    }
		
		public static void preRegisteryModelBakeryStuff(Block block)
		{
			ModelBakery.registerItemVariants
			(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(0)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(1)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(2)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(3))
			);
		}

		static
		{
			for (EnumDamaged enumDamaged : values())
			{
				META_LOOKUP[enumDamaged.getMeta()] = enumDamaged;
			}
		}
	}

	/*public enum EnumSeason implements IEnumHandler
	{
		SPRING(0, "spring"),
		SUMMER(1, "summer"),
		AUTUMN(2, "autumn"),
		WINTER(3, "winter");
		
	    private static final EnumSeason[] META_LOOKUP = new EnumSeason[values().length];
		private int meta;
		private String name;
		
		private EnumSeason(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		@Override
		public int getMeta()	{return meta;}
		
		@Override
		public String getName()	{return name;}
		
		@Override
		public String toString(){return getName();}

		public static EnumSeason getEnumByMeta(int meta)
	    {
	        return getIEnumByMeta(META_LOOKUP, meta);
	    }

		public static int getMaxMeta()
		{
			return META_LOOKUP.length;
		}

		public static String getSpecialName(int meta)
	    {
	        return getEnumByMeta(meta).getName();
	    }
		
		public static void preRegisteryModelBakeryStuff(Block block)
		{
			ModelBakery.registerItemVariants
			(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(0)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(1)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(2)),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(3))
			);
		}

		static
		{
			for (EnumSeason enumSeason : values())
			{
				META_LOOKUP[enumSeason.getMeta()] = enumSeason;
			}
		}
	}*/

	public enum EnumFloricFlower implements IEnumHandler
	{
		LYCORIS(0, "lycoris");

		private static final EnumFloricFlower[] META_LOOKUP = new EnumFloricFlower[values().length];
		private int meta;
		private String name;
		
		private EnumFloricFlower(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		@Override
		public int getMeta()	{return meta;}
		
		@Override
		public String getName()	{return name;}
		
		@Override
		public String toString(){return getName();}

		public static EnumFloricFlower getEnumByMeta(int meta)
	    {
	        return getIEnumByMeta(META_LOOKUP, meta);
	    }

		public static int getMaxMeta()
		{
			return META_LOOKUP.length;
		}

		public static String getSpecialName(int meta)
	    {
	        return getEnumByMeta(meta).getName();
	    }
		
		public static void preRegisteryModelBakeryStuff(Block block)
		{
			ModelBakery.registerItemVariants
			(
				Item.getItemFromBlock(block),
				new ResourceLocation(Reference.MOD_ID, block.getUnlocalizedName().substring(5) + "_" + getSpecialName(0))
			);
		}

		static
		{
			for (EnumFloricFlower enumFlower : values())
			{
				META_LOOKUP[enumFlower.getMeta()] = enumFlower;
			}
		}
	}
}
