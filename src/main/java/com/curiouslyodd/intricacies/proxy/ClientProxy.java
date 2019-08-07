package com.curiouslyodd.intricacies.proxy;

import com.curiouslyodd.intricacies.init.ModEntities;
import com.curiouslyodd.intricacies.init.ModItems;
import com.curiouslyodd.intricacies.init.ModKeyBindings;
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
        
        ModEntities.initModels();
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
    	ModKeyBindings.registerKeyBindings();
    }
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    	ModItems.initModels();
    }
}