package com.curiouslyodd.intricacies.events;

import com.curiouslyodd.intricacies.gameplay.Temperature;

import net.minecraft.command.ICommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class LivingEvents {

	/**
	 * LivingUpdateEvent
	 * 
	 * Called constantly on all entities, currently used to
	 * detect temperature changes based on biomes etc.
	 * 
	 * @param event
	 */
	public static void LivingUpdateEvent(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			// Check for temperature changes.
			Temperature.checkBiome(event);
		}
	}
	
	/**
	 * EntityJoinWorldEvent
	 * 
	 * Called when an entity joins the world, currently used to
	 * set gamerule naturalRegeneration to false.
	 * 
	 * @param event
	 */
	public static void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if(event.getWorld().isRemote) return;
		
		if(event.getEntity() instanceof EntityPlayer) {		
			ICommandManager manager = event.getWorld().getMinecraftServer().commandManager;
			manager.executeCommand(event.getEntity(), "/gamerule naturalRegeneration false");
		}
	}
	
}