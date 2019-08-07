package com.curiouslyodd.intricacies.proxy;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.blocks.FirstBlock;
import com.curiouslyodd.intricacies.capabilities.CapabilityHandler;
import com.curiouslyodd.intricacies.capabilities.skills.ISkill;
import com.curiouslyodd.intricacies.capabilities.skills.SkillFactory;
import com.curiouslyodd.intricacies.capabilities.skills.SkillStorage;
import com.curiouslyodd.intricacies.init.ModBlocks;
import com.curiouslyodd.intricacies.init.ModEntities;
import com.curiouslyodd.intricacies.init.ModItems;
import com.curiouslyodd.intricacies.network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
        CapabilityManager.INSTANCE.register(ISkill.class, new SkillStorage(), new SkillFactory());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        PacketHandler.registerMessages(Main.MODID);
        ModEntities.init();
	}

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	event.getRegistry().register(new FirstBlock());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	// Items
    	ModItems.initItems(event);
    	
    	// Blocks
    	event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
    }
}