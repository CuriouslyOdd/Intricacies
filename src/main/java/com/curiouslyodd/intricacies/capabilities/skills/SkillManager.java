package com.curiouslyodd.intricacies.capabilities.skills;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.init.SoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SkillManager {
	
	public static int experiencePerAction = 100;
	
	/**
	 * Add Experience
	 * 
	 * Adds a set amount of experience to the provided skill and player, this will automatically
	 * level up any skills if they surpass the threshold.
	 * 
	 * @param player
	 * @param key
	 * @param experience
	 */
	public static void addExperience(EntityPlayer player, String key, int experience) {
		ISkill skills = player.getCapability(SkillProvider.SKILL_CAP, null);
		
		// Add experience
		skills.addExperience(key, experience);
		
		// Do they require a level up?
		if(skills.shouldLevelUp(key)) {
			skills.doLevelUp(key);
			
			// Notify player of level up.
			Main.guiToastHandler.showToast("Skill levelled up!", key + " is now level " + skills.getLevel(key));
			Minecraft.getMinecraft().player.playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
		}
	}
	
	/**
	 * Build Skill List
	 * 
	 * This builds an NBTTagCompound list of all the provided players skills, mainly used
	 * for populating GUIs.
	 * 
	 * @param player
	 * @return
	 */
	public static NBTTagCompound buildSkillList(EntityPlayer player) {
		NBTTagCompound tags = new NBTTagCompound();
		ISkill skills = player.getCapability(SkillProvider.SKILL_CAP, null);
		
		tags.setInteger("swordfighting_experience", skills.getExperience("swordfighting"));
		tags.setInteger("swordfighting_level", skills.getLevel("swordfighting"));
		
		tags.setInteger("archery_experience", skills.getExperience("archery"));
		tags.setInteger("archery_level", skills.getLevel("archery"));
		
		tags.setInteger("magic_experience", skills.getExperience("magic"));
		tags.setInteger("magic_level", skills.getLevel("magic"));
		
		tags.setInteger("block_experience", skills.getExperience("block"));
		tags.setInteger("block_level", skills.getLevel("block"));
		
		tags.setInteger("woodcutting_experience", skills.getExperience("woodcutting"));
		tags.setInteger("woodcutting_level", skills.getLevel("woodcutting"));
		
		return tags;
	}
	
	/**
	 * Clone Player
	 * 
	 * Copies all skills and experience over to a cloned player (when they die) so
	 * they don't lose their progress.
	 * 
	 * @param event
	 */
	public static void clonePlayer(PlayerEvent.Clone event) {
		EntityPlayer player = event.getEntityPlayer();
		
		ISkill skills = player.getCapability(SkillProvider.SKILL_CAP, null);
		ISkill oldSkills = event.getOriginal().getCapability(SkillProvider.SKILL_CAP, null);
		
		skills.setLevel("swordfighting", oldSkills.getLevel("swordfighting"));
		//skills.setExperience("swordfighting", oldSkills.getExperience("swordfighting"));
		
		skills.setLevel("archery", oldSkills.getLevel("archery"));
		//skills.setExperience("archery", oldSkills.getExperience("archery"));
		
		skills.setLevel("magic", oldSkills.getLevel("magic"));
		//skills.setExperience("magic", oldSkills.getExperience("magic"));
		
		skills.setLevel("block", oldSkills.getLevel("block"));
		//skills.setExperience("block", oldSkills.getExperience("block"));
		
		skills.setLevel("woodcutting", oldSkills.getLevel("woodcutting"));
		//skills.setExperience("woodcutting", oldSkills.getExperience("woodcutting"));
	}
}