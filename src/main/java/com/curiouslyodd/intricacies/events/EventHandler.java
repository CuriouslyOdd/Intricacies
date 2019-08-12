package com.curiouslyodd.intricacies.events;

import java.util.List;
import java.util.Random;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.capabilities.skills.SkillManager;
import com.curiouslyodd.intricacies.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
			SkillManager.addExperience(player, "swordfighting");
		}
		
		// Held item is a bow.
		else if(heldItemMain instanceof ItemBow) {
			SkillManager.addExperience(player, "archery");
		}
	}
	
	/**
	 * LivingUpdateEvent
	 * @param event
	 */
	@SubscribeEvent
	public static void onEvent(LivingUpdateEvent event) {
		LivingEvents.LivingUpdateEvent(event);
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
				SkillManager.addExperience(player, "block");
			}
			
		}
	}
	
	/**
	 * EntityJoinWorldEvent
	 * @param event
	 */
	@SubscribeEvent
	public static void onEvent(EntityJoinWorldEvent event) {
		LivingEvents.EntityJoinWorldEvent(event);
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
		System.out.println("BlockEvent.BreakEvent");
		
		// Exit out if player isn't set.
		if(event.getPlayer() == null) return;
		
		// Double check it was a player who broke the block.
		if(event.getPlayer() instanceof EntityPlayer) {			
			EntityPlayer player = event.getPlayer();
			Item itemBeingUsed = player.getHeldItemMainhand().getItem();
			Block block = event.getState().getBlock();
			World world = event.getWorld();
			BlockPos blockPos = event.getPos();
			
			// Skill check for breaking blocks with specific items. (Woodcutting, mining, etc).
			SkillManager.blockBrokenExperienceCheck(block, player, itemBeingUsed);
			
			// Broke flaming wood event.
			BlockEvents.brokeFlamingWood(block, blockPos, world);
			
			// Drop rocks when breaking stone without tools.
			BlockEvents.dropRocksForStone(block, itemBeingUsed, world, blockPos);
		}
	}
	
	/**
	 * HarvestDropsEvent
	 * 
	 * Whenever a block is harvested for it's drops, used to modify the
	 * drop list of any vanilla blocks.
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(BlockEvent.HarvestDropsEvent event) {
		// Do nothing if this isn't a player.
		if(event.getHarvester() == null) return;
		
		// Commonly used variables.
		Block harvestedBlock = event.getState().getBlock();
		List<ItemStack> drops = event.getDrops();
		Item itemBeingUsed = event.getHarvester().getHeldItemMainhand().getItem();
		
		// If harvesting grass.
		if(harvestedBlock instanceof BlockTallGrass) {
			drops.clear(); // Remove all current drops (seeds, etc).
			drops.add(new ItemStack(ModItems.itemPlantFibers)); // Add our plant fibers to the drop list.
		}
		
		// If harvesting leaves.
		if(harvestedBlock instanceof BlockLeaves) {
			// Leaves harvested with an axe have a 100% stick drop rate, otherwise a 1 in 3 drop rate.
			int sticksToDrop = (itemBeingUsed instanceof ItemAxe) ? (new Random().nextInt(2) + 1) : (new Random().nextInt(3) == 3) ? 1 : 0;
			drops.add(new ItemStack(Items.STICK, sticksToDrop));
		}
		
		// If harvesting logs without using an axe, lesser the drop chance to 10%.
		if(harvestedBlock instanceof BlockLog && !(itemBeingUsed instanceof ItemAxe)) {
			event.setDropChance(0.1f);
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
}