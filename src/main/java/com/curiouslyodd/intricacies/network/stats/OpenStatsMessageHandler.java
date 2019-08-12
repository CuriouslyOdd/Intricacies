package com.curiouslyodd.intricacies.network.stats;

import com.curiouslyodd.intricacies.client.guis.GuiStats;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenStatsMessageHandler implements IMessageHandler<OpenStatsMessage, IMessage> {

	@Override
	public IMessage onMessage(OpenStatsMessage message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
		return null;
	}
	
	private void handle(OpenStatsMessage message, MessageContext ctx) {
		NBTTagCompound stats = message.toSend;
		Minecraft.getMinecraft().displayGuiScreen(new GuiStats(stats));
	}
	
}