package com.curiouslyodd.intricacies;

import com.curiouslyodd.intricacies.capabilities.skills.SkillManager;
import com.curiouslyodd.intricacies.init.ModKeyBindings;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

@EventBusSubscriber(modid = Main.MODID)
public class EventHandler {
	
	/**
	 * AttackEntityEvent
	 * 
	 * Runs when the player attacks an entity, mainly used to give experience to players
	 * whenever applicable. (Swordfighting, archery, etc).
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(AttackEntityEvent event) {
		EntityPlayer player = (EntityPlayer) event.getEntity();
		Item heldItemMain = player.getHeldItemMainhand().getItem();
		
		// Return if isRemote.
		if(player.getEntityWorld().isRemote) return;
		
		// Held item is a sword.
		if(heldItemMain instanceof ItemSword) {
			SkillManager.addExperience(player, "swordfighting", SkillManager.experiencePerAction);
		}
		
		// Held item is a bow.
		else if(heldItemMain instanceof ItemBow) {
			SkillManager.addExperience(player, "archery", SkillManager.experiencePerAction);
		}
	}
	
	/**
	 * LivingAttackEvent
	 * 
	 * Whenever a living entity is attacked, mostly used to level applicable stats when
	 * using certain weapons.
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(LivingAttackEvent event) {
		// If the player was attacked.
		if(event.getEntity() instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) event.getEntity();
			Item itemBeingUsed = player.getActiveItemStack().getItem();
			
			if(itemBeingUsed instanceof ItemShield) {
				SkillManager.addExperience(player, "block", SkillManager.experiencePerAction);
			}
			
		}
	}
	
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(LivingEntityUseItemEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			Item item = event.getItem().getItem();
			
			player.sendMessage(new TextComponentString(item.getUnlocalizedName()));
		}
	}
	
	/**
	 * BlockBreakEvent
	 * 
	 * Called when the player(?) breaks a block, used to level up any skills
	 * relating to block breaking (woodcutting etc).
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(BlockEvent.BreakEvent event) {
		
		// Double check it was a player who broke the block.
		if(event.getPlayer() instanceof EntityPlayer) {			
			EntityPlayer player = event.getPlayer();
			Item itemBeingUsed = player.getHeldItemMainhand().getItem();
			Block block = event.getState().getBlock();
			
			// If block is log.
			if(block instanceof BlockLog) {
				// If an axe is used assign woodcutting XP.
				if(itemBeingUsed instanceof ItemAxe) {
					SkillManager.addExperience(player, "woodcutting", SkillManager.experiencePerAction);
				}
				
				// Otherwise cancel the break.
				else {
					event.setCanceled(true);
					return;
				}
			}
		}
	}
	
	/**
	 * PlayerCloneEvent
	 * 
	 * Whenever a player dies and they are cloned, we use this to
	 * repopulate all their old skills into the clone so they dont
	 * lose any progress. They lose all experience though.
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
		SkillManager.clonePlayer(event);
	}
	
	/**
	 * KeyInputEvent
	 * 
	 * Currently used to check for any keybound key presses.
	 * Should probably be replaced with a tickCheck(?) handler at some point.
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(KeyInputEvent event) {
	    KeyBinding[] keyBindings = ModKeyBindings.keyBindings;
	    	    
	    if(keyBindings[0].isPressed()) {
	    }
	}
}