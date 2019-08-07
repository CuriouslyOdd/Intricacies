package com.curiouslyodd.intricacies.client.guis;

import com.curiouslyodd.intricacies.Main;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiStats extends GuiScreen {
	
	private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation(Main.MODID + ":textures/gui/stats.png");
	int guiWidth = 256;
	int guiHeight = 181;
	NBTTagCompound stats = null;
	
	public GuiStats(NBTTagCompound stats) {
		this.stats = stats;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		mc.getTextureManager().bindTexture(GUI_BACKGROUND);
		
		// Background
		int centerX = (width / 2) - guiWidth / 2;
		int centerY = (height / 2) - guiHeight / 2;

		drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
		
		// Heading
		int labelX = centerX + 45;
		int labelY = centerY + 19;
		
		// Stats
		fontRenderer.drawString("" + stats.getInteger("swordfighting_level") + " (" + stats.getInteger("swordfighting_experience") + "/" + (stats.getInteger("swordfighting_level") * 100) + ")", labelX, labelY, 0x000000);
		fontRenderer.drawString("" + stats.getInteger("archery_level") + " (" + stats.getInteger("archery_experience") + "/" + (stats.getInteger("archery_level") * 100) + ")", labelX, (labelY + 22), 0x000000);
		fontRenderer.drawString("" + stats.getInteger("magic_level") + " (" + stats.getInteger("magic_experience") + "/" + (stats.getInteger("magic_level") * 100) + ")", labelX, (labelY + 44), 0x000000);
		fontRenderer.drawString("" + stats.getInteger("block_level") + " (" + stats.getInteger("block_experience") + "/" + (stats.getInteger("block_level") * 100) + ")", labelX, (labelY + 66), 0x000000);
		fontRenderer.drawString("" + stats.getInteger("woodcutting_level") + " (" + stats.getInteger("woodcutting_experience") + "/" + (stats.getInteger("woodcutting_level") * 100) + ")", labelX, (labelY + 88), 0x000000);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
}