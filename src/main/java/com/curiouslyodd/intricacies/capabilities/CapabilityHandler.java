package com.curiouslyodd.intricacies.capabilities;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.capabilities.skills.SkillProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation SKILL_CAP = new ResourceLocation(Main.MODID, "skills");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (!(event.getObject() instanceof EntityPlayer)) return;
		event.addCapability(SKILL_CAP, new SkillProvider());
	}
	
}