package com.curiouslyodd.intricacies.capabilities;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.capabilities.skills.ISkill;
import com.curiouslyodd.intricacies.capabilities.skills.SkillFactory;
import com.curiouslyodd.intricacies.capabilities.skills.SkillProvider;
import com.curiouslyodd.intricacies.capabilities.skills.SkillStorage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation SKILL_CAP = new ResourceLocation(Main.MODID, "skills");
	public static final ResourceLocation QUEST_CAP = new ResourceLocation(Main.MODID, "quests");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (!(event.getObject() instanceof EntityPlayer)) return;
		
		event.addCapability(SKILL_CAP, new SkillProvider());
	}
	
	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(ISkill.class, new SkillStorage(), new SkillFactory());

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}
	
}