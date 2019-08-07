package com.curiouslyodd.intricacies.capabilities.skills;

public class Skill implements ISkill {
	
	private int swordfighting_level = 1;
	private int swordfighting_experience = 0;
	
	private int archery_level = 1;
	private int archery_experience = 0;
	
	private int magic_level = 1;
	private int magic_experience = 0;
	
	private int block_level = 1;
	private int block_experience = 0;
	
	private int woodcutting_level = 1;
	private int woodcutting_experience = 0;
	
	/**
	 * Levels
	 */
	
	// Add level
	public void addLevel(String key, int level) {
		switch(key) {
			case "swordfighting":
				swordfighting_level += level;
			break;
			case "archery":
				archery_level += level;
			break;
			case "magic":
				magic_level += level;
			break;
			case "block":
				block_level += level;
			break;
			case "woodcutting":
				woodcutting_level += level;
			break;
		}
	}
	
	// Set level
	public void setLevel(String key, int level) {
		switch(key) {
			case "swordfighting":
				swordfighting_level = level;
			break;
			case "archery":
				archery_level = level;
			break;
			case "magic":
				magic_level = level;
			break;
			case "block":
				block_level = level;
			break;
			case "woodcutting":
				woodcutting_level = level;
			break;
		}
	}
	
	// Get level
	public int getLevel(String key) {
		int return_value = 0;
		
		switch(key) {
			case "swordfighting":
				return_value = swordfighting_level;
			break;
			case "archery":
				return_value = archery_level;
			break;
			case "magic":
				return_value = magic_level;
			break;
			case "block":
				return_value = block_level;
			break;
			case "woodcutting":
				return_value = woodcutting_level;
			break;
		}
		
		return return_value;
	}
	
	/**
	 * Experience
	 */
	
	// Add experience
	public void addExperience(String key, int experience) {
		switch(key) {
			case "swordfighting":
				swordfighting_experience += experience;
			break;
			case "archery":
				archery_experience += experience;
			break;
			case "magic":
				magic_experience += experience;
			break;
			case "block":
				block_experience += experience;
			break;
			case "woodcutting":
				woodcutting_experience += experience;
			break;
		}
	}
	
	// Set experience
	public void setExperience(String key, int experience) {
		switch(key) {
			case "swordfighting":
				swordfighting_experience = experience;
			break;
			case "archery":
				archery_experience = experience;
			break;
			case "magic":
				magic_experience = experience;
			break;
			case "block":
				block_experience = experience;
			break;
			case "woodcutting":
				woodcutting_experience = experience;
			break;
		}
	}
	
	// Get experience
	public int getExperience(String key) {
		int return_value = 0;
		
		switch(key) {
			case "swordfighting":
				return_value = swordfighting_experience;
			break;
			case "archery":
				return_value = archery_experience;
			break;
			case "magic":
				return_value = magic_experience;
			break;
			case "block":
				return_value = block_experience;
			break;
			case "woodcutting":
				return_value = woodcutting_experience;
			break;
		}
		
		return return_value;
	}
	
	/**
	 * Level up
	 */
	
	// Should level up
	public boolean shouldLevelUp(String key) {
		int current_level = this.getLevel(key);
		int current_experience = this.getExperience(key);
		
		if(current_experience >= (current_level * 100)) return true;
		return false;
	}
	
	// Do level up
	public void doLevelUp(String key) {
		int current_level = this.getLevel(key);
		int current_experience = this.getExperience(key);
		
		this.addLevel(key, 1);
		this.setExperience(key, (current_experience - (current_level * 100)));
	}
}