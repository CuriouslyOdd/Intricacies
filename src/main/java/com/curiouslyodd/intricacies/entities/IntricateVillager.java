package com.curiouslyodd.intricacies.entities;

import javax.annotation.Nonnull;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class IntricateVillager extends EntityVillager {

	public IntricateVillager() {
		super(null);
        this.setSize(0.4F, 0.4F);
	}
	
	public IntricateVillager(World worldIn) {
		super(worldIn);
	}
	
	@Override
    public boolean processInteract(EntityPlayer player, @Nonnull EnumHand hand) {
		System.out.println("Interacted");
		return true;
	}
	
}