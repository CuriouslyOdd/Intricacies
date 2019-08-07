package com.curiouslyodd.intricacies.capabilities.skills;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SkillStorage implements IStorage<ISkill> {

	@Override
	public NBTBase writeNBT(Capability<ISkill> capability, ISkill instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		
		tag.setInteger("swordfighting_level", instance.getLevel("swordfighting"));
		tag.setInteger("swordfighting_experience", instance.getExperience("swordfighting"));
		
		tag.setInteger("magic_level", instance.getLevel("magic"));
		tag.setInteger("magic_experience", instance.getExperience("magic"));
		
		tag.setInteger("archery_level", instance.getLevel("archery"));
		tag.setInteger("archery_experience", instance.getExperience("archery"));
		
		tag.setInteger("block_level", instance.getLevel("block"));
		tag.setInteger("block_experience", instance.getExperience("block"));
		
		tag.setInteger("woodcutting_level", instance.getLevel("woodcutting"));
		tag.setInteger("woodcutting_experience", instance.getExperience("woodcutting"));
		
		return tag;
	}
	
	@Override
	public void readNBT(Capability<ISkill> capability, ISkill instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound nbttc = (NBTTagCompound) nbt;
		
		instance.setLevel("swordfighting", nbttc.getInteger("swordfighting_level"));
		instance.setExperience("swordfighting", nbttc.getInteger("swordfighting_experience"));
		
		instance.setLevel("archery", nbttc.getInteger("archery_level"));
		instance.setExperience("archery", nbttc.getInteger("archery_experience"));
		
		instance.setLevel("magic", nbttc.getInteger("magic_level"));
		instance.setExperience("magic", nbttc.getInteger("magic_experience"));
		
		instance.setLevel("block", nbttc.getInteger("block_level"));
		instance.setExperience("block", nbttc.getInteger("block_experience"));
		
		instance.setLevel("woodcutting", nbttc.getInteger("woodcutting_level"));
		instance.setExperience("woodcutting", nbttc.getInteger("woodcutting_experience"));
	}
	
}