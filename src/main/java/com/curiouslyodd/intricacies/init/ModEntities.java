package com.curiouslyodd.intricacies.init;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.client.render.RenderIntricateVillager;
import com.curiouslyodd.intricacies.entities.IntricateVillager;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {
	
	private static int mobId = 0;
	
	public static int nextID() {
		return mobId++;
	}

	public static void init() {
        EntityRegistry.registerModEntity(RenderIntricateVillager.mobTexture, IntricateVillager.class, "IntricateVillager", nextID(), Main.instance, 64, 3, true, 0x996600, 0x00ff00);
        //LootTableList.register(EntityWeirdZombie.LOOT);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(IntricateVillager.class, RenderIntricateVillager.FACTORY);
    }
	
}
