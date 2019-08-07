package com.curiouslyodd.intricacies.network;

import com.curiouslyodd.intricacies.client.guis.GuiStats;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenStatsMessageHandler implements IMessageHandler<OpenStatsMessage, IMessage> {

	@Override
	public IMessage onMessage(OpenStatsMessage message, MessageContext ctx) {
		NBTTagCompound stats = message.toSend;
		
		Minecraft.getMinecraft().displayGuiScreen(new GuiStats(stats));
		
		return null;
	}
	
}
