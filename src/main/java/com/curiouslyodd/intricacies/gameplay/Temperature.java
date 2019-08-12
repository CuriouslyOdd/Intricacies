package com.curiouslyodd.intricacies.gameplay;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class Temperature {

	private static int biomeCheckDelayAmount = 20 * 5; // 5 seconds
	private static int biomeCheckDelayCounter = biomeCheckDelayAmount;
	
	/**
	 * CheckBiome
	 * 
	 * A simple delay on updates to stop the biome processing from
	 * happening every tick, then calls the biome update checker.
	 * 
	 * @param event
	 */
	public static void checkBiome(LivingUpdateEvent event) {
		biomeCheckDelayCounter--; // Decrement the delay.
		
		// If the delay is done.
		if(biomeCheckDelayCounter <= 0) {
			updateBiome(event);			
			biomeCheckDelayCounter = biomeCheckDelayAmount; // Reset the delay count.
		}
	}
	
	/**
	 * UpdateBiome
	 * 
	 * Called every x seconds to check the current players biome and
	 * then fires off all related methods for processing the changes
	 * in temperature.
	 * 
	 * @param event
	 */
	public static void updateBiome(LivingUpdateEvent event) {
		EntityPlayer player = (EntityPlayer) event.getEntityLiving(); // The current player
		Biome biome = player.world.getBiome(player.getPosition()); // The current biome
		
		System.out.println(biome.getBiomeName() + ": " +  biome.getDefaultTemperature());
	}
}