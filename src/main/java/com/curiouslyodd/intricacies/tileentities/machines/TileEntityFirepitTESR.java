package com.curiouslyodd.intricacies.tileentities.machines;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityFirepitTESR extends TileEntitySpecialRenderer<TileEntityFirepit> {

	public void render(TileEntityFirepit tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();
		
		renderItem(tileEntity);
		
		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
	}
	
	private void renderItem(TileEntityFirepit tileEntity) {
		ItemStack stack = tileEntity.getStack();
		if(!stack.isEmpty()) {
			RenderHelper.enableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
            GlStateManager.translate(.3, .3, 0.8);
            GlStateManager.scale(.4f, .4f, .4f);

            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

            GlStateManager.popMatrix();
		}
		
		ItemStack stackOut = tileEntity.getStackOut();
		if(!stackOut.isEmpty()) {
			GlStateManager.enableLighting();
            GlStateManager.pushMatrix();
            GlStateManager.translate(.7, .3, 0.8);
            GlStateManager.scale(.4f, .4f, .4f);
            
            Minecraft.getMinecraft().getRenderItem().renderItem(stackOut, ItemCameraTransforms.TransformType.NONE);
            
            GlStateManager.popMatrix();
		}
	}
	
}
