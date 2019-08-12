package com.curiouslyodd.intricacies.proxy;

import com.curiouslyodd.intricacies.blocks.ModBlocks;
import com.curiouslyodd.intricacies.entities.ModEntities;
import com.curiouslyodd.intricacies.items.ModItems;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
    	
    }
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    	ModEntities.initModels();
    	ModItems.initModels();
        ModBlocks.initModels();
    }
}