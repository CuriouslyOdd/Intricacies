package com.curiouslyodd.intricacies.items.resources;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPlantFibers extends Item {
	public ItemPlantFibers() {
		setUnlocalizedName(Main.MODID + ":plant_fibers");
		setRegistryName("plant_fibers");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}