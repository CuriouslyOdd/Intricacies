package com.curiouslyodd.intricacies.items;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.capabilities.skills.ISkill;
import com.curiouslyodd.intricacies.capabilities.skills.SkillProvider;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAppleOfResetting extends ItemFood {

	public ItemAppleOfResetting(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(Main.MODID + ":apple_of_resetting");
		setRegistryName("apple_of_resetting");
		setAlwaysEdible();
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		// Exit if isRemote.
		if(player.getEntityWorld().isRemote) return;
		
		// Get the players skills capability.
		ISkill skills = player.getCapability(SkillProvider.SKILL_CAP, null);
		
		// Reset levels and experience.
		skills.setLevel("swordfighting", 1);
		skills.setExperience("swordfighting", 0);
		
		skills.setLevel("archery", 1);
		skills.setExperience("archery", 0);
		
		skills.setLevel("magic", 1);
		skills.setExperience("magic", 0);
		
		skills.setLevel("block", 1);
		skills.setExperience("block", 0);
		
		skills.setLevel("woodcutting", 1);
		skills.setExperience("woodcutting", 0);
		
		// Notify player of changes.
		player.sendMessage(new TextComponentString("All levels reset to 0"));
		player.sendMessage(new TextComponentString("All experience reset to 0"));
	}
	
}
