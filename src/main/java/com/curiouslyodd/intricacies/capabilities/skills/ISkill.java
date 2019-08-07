package com.curiouslyodd.intricacies.capabilities.skills;

public interface ISkill {
	
	// Levels
	public void addLevel(String key, int level);
	public void setLevel(String key, int level);
	public int getLevel(String key);
	
	// Experience
	public void addExperience(String key, int experience);
	public void setExperience(String key, int experience);
	public int getExperience(String key);
	
	// Level up
	public boolean shouldLevelUp(String key);
	public void doLevelUp(String key);
}