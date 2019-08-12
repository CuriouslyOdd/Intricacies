package com.curiouslyodd.intricacies.proxy;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.blocks.ModBlocks;
import com.curiouslyodd.intricacies.capabilities.CapabilityHandler;
import com.curiouslyodd.intricacies.entities.ModEntities;
import com.curiouslyodd.intricacies.items.ModItems;
import com.curiouslyodd.intricacies.network.PacketHandler;
import com.curiouslyodd.intricacies.tileentities.machines.TileEntityFirepit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		CapabilityHandler.registerCapabilities();
        PacketHandler.registerMessages(Main.MODID);
        ModEntities.init();
	}

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	ModBlocks.registerBlocks(event);
    	GameRegistry.registerTileEntity(TileEntityFirepit.class, Main.MODID + "_firepit");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	ModItems.registerItems(event);
    	ModBlocks.registerBlockItems(event);
    }
    
    
}