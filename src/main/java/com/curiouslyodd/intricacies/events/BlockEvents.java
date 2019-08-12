package com.curiouslyodd.intricacies.events;

import java.util.Random;

import com.curiouslyodd.intricacies.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEvents {

	/**
	 * BrokeFlamingWood
	 * 
	 * When a player breaks a wooden block (Log/Planks) that are on fire
	 * we drop charcoal.
	 * 
	 * @param block
	 * @param blockPos
	 * @param world
	 */
	public static void brokeFlamingWood(Block block, BlockPos blockPos, World world) {
		if(block instanceof BlockLog || block instanceof BlockPlanks) {
			BlockPos firePos = blockPos.up();
			Block fireBlock = world.getBlockState(firePos).getBlock();
			
			if(fireBlock instanceof BlockFire) {
				// Drop 1 - 6 charcoal.
				world.spawnEntity(new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(Items.COAL, (new Random().nextInt(5) + 1), 1)));
			}
		}
	}
	
	/**
	 * DropRocksForStone
	 * 
	 * When a player breaks stone without a tool rocks are dropped instead
	 * 
	 * @param block
	 * @param itemBeingUsed
	 * @param world
	 * @param blockPos
	 */
	public static void dropRocksForStone(Block block, Item itemBeingUsed, World world, BlockPos blockPos) {
		if(block instanceof BlockStone) {
			// If we aren't using a pickaxe.
			if(!(itemBeingUsed instanceof ItemPickaxe)) {
				if(!world.isRemote) {
					// Spawn between 1 - 3 rocks.
					world.spawnEntity(new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(ModItems.itemRock, (new Random().nextInt(2) + 1))));
				}
			}
		}
	}
	
}
