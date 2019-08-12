package com.curiouslyodd.intricacies.network.stats;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class OpenStatsMessage implements IMessage {

	public OpenStatsMessage() {}

	NBTTagCompound toSend;
	
	public OpenStatsMessage(NBTTagCompound toSend) {
		this.toSend = toSend;
	}
	
	@Override public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, toSend);
	}

	 @Override public void fromBytes(ByteBuf buf) {
		 toSend = ByteBufUtils.readTag(buf);
	 }
}
