package com.curiouslyodd.intricacies.client.guis;

import org.lwjgl.opengl.GL11;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiToast extends Gui {
	
	private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation(Main.MODID + ":textures/gui/toasts.png");
	int guiWidth = 160;
	int guiHeight = 32;
	
	public GuiToast(Minecraft mc) {
		mc.getTextureManager().bindTexture(GUI_BACKGROUND);
		
		// Background
		drawTexturedModalRect(5, 5, 0, 0, guiWidth, guiHeight);
		
		NBTTagCompound displayed = Main.guiToastHandler.toastTagList.getCompoundTagAt(0);
		
		drawString(mc.fontRenderer, displayed.getString("title"), 15, 12, 0xFFFFFF);
		
		GL11.glScaled(0.9, 0.9, 0.9);
		
		String capitalisedMessage = displayed.getString("message").substring(0, 1).toUpperCase() + displayed.getString("message").substring(1);
		drawString(mc.fontRenderer, capitalisedMessage, 17, 25, 0xAAAAAA);
	}
	
}