package com.curiouslyodd.intricacies;

import org.apache.logging.log4j.Logger;

import com.curiouslyodd.intricacies.client.guis.GuiHandler;
import com.curiouslyodd.intricacies.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class Main {

	public static final String MODID = "intricacies";
	public static final String MODNAME = "Intricacies";
	public static final String MODVERSION = "0.0.1";
	
	
	@SidedProxy(clientSide = "com.curiouslyodd.intricacies.proxy.ClientProxy", serverSide = "com.curiouslyodd.intricacies.proxy.ServerProxy")
    public static CommonProxy proxy;
	
	@Mod.Instance
    public static Main instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        RecipeHandler.removeRecipes();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
        GuiHandler.registerGuis();
    }
}