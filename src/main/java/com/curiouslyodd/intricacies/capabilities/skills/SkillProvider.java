package com.curiouslyodd.intricacies.capabilities.skills;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SkillProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(ISkill.class)
	public static final Capability<ISkill> SKILL_CAP = null;
	
	//private ISkill instance = SKILL_CAP.getDefaultInstance();
	private ISkill instance = SKILL_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == SKILL_CAP;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == SKILL_CAP ? SKILL_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return SKILL_CAP.getStorage().writeNBT(SKILL_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		SKILL_CAP.getStorage().readNBT(SKILL_CAP, this.instance, null, nbt);
	}
	
}
