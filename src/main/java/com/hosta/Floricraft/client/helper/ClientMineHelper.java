package com.hosta.Floricraft.client.helper;

import com.hosta.Floricraft.client.particle.ParticleFloric;
import com.hosta.Floricraft.helper.DateHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientMineHelper {

	public static void spawnFloricParticle(World world, double[] pos, int meta, int type)
	{
		ParticleFloric particle = new ParticleFloric(world, pos[0], pos[1], pos[2], meta, type);
		
		if (DateHelper.isAprilFool())
		{
			particle = new ParticleFloric(world, pos[0], pos[1], pos[2], Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.FISH), type);
		}
				
		particle.generateParticle();
	}
}