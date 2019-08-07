package com.curiouslyodd.intricacies.client.guis;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiToastHandler {
	
	private static Timer timer = new Timer();
	private static TimerTask removeToastTimerTask;
	public boolean timerRunning = false;
	
	public NBTTagList toastTagList = new NBTTagList();
	
	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {
		if (event.getType() != ElementType.EXPERIENCE) return;
		if(toastTagList.tagCount() > 0) {
			new GuiToast(Minecraft.getMinecraft());
			
			if(!timerRunning) {
				timerRunning = true;
				removeToastTimerTask = new RemoveToastTimerTask(this);
				timer.schedule(removeToastTimerTask, 2000);
			}
		}
    }
	
	public void showToast(String title, String message) {
		NBTTagCompound toast = new NBTTagCompound();
		toast.setString("title", title);
		toast.setString("message", message);
		
		toastTagList.appendTag(toast);
	}
	
}

class RemoveToastTimerTask extends TimerTask {	
	GuiToastHandler context;
	
	public RemoveToastTimerTask(GuiToastHandler context) {
		this.context = context;
	}
	
	@Override
	public void run() {
		context.toastTagList.removeTag(0);
		context.timerRunning = false;
	}
}