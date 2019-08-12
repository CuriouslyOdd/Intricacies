package com.curiouslyodd.intricacies.blocks;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.blocks.machines.BlockFirepit;
import com.curiouslyodd.intricacies.blocks.resources.BlockCharcoal;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	@GameRegistry.ObjectHolder(Main.MODID + ":firstblock")
	public static FirstBlock firstBlock;
	
	// Machines
	@GameRegistry.ObjectHolder(Main.MODID + ":firepit")
	public static BlockFirepit blockFirepit;
	
	// Resources
	@GameRegistry.ObjectHolder(Main.MODID + ":charcoal")
	public static BlockCharcoal blockCharcoal;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		firstBlock.initModel();
		
		// Machines
		blockFirepit.initModel();
		
		// Resources
		blockCharcoal.initModel();
	}
	
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new FirstBlock());
		
		// Machines
		event.getRegistry().register(new BlockFirepit());
		
		// Resources
		event.getRegistry().register(new BlockCharcoal());
	}
	
	public static void registerBlockItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
		
		// Machines
		event.getRegistry().register(new ItemBlock(ModBlocks.blockFirepit).setRegistryName(ModBlocks.blockFirepit.getRegistryName()));
		
		// Resources
		event.getRegistry().register(new ItemBlock(ModBlocks.blockCharcoal).setRegistryName(ModBlocks.blockCharcoal.getRegistryName()));
	}
}