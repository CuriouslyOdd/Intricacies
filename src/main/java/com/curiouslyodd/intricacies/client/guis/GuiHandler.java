package com.curiouslyodd.intricacies.client.guis;

import com.curiouslyodd.intricacies.client.guis.toast.GuiToastHandler;

import net.minecraftforge.common.MinecraftForge;

public class GuiHandler {
	public static GuiToastHandler guiToastHandler = new GuiToastHandler();
	
	public static void registerGuis() {
		MinecraftForge.EVENT_BUS.register(guiToastHandler);
	}
}
