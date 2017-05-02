package com.hosta.Floricraft.block;

public class BlockFlowerLycoris extends BlockBasicBush {

	public BlockFlowerLycoris(String name)
	{
		super(name);
	}
	
	@Override
	public EnumOffsetType getOffsetType()
	{
		return EnumOffsetType.XZ;
	}
}
