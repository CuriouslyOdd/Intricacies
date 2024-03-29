package com.curiouslyodd.intricacies.items.resources;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRock extends Item {
	public ItemRock() {
		setUnlocalizedName(Main.MODID + ":rock");
		setRegistryName("rock");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
