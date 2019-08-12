package com.curiouslyodd.intricacies.blocks.resources;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCharcoal extends Block {
	
	public BlockCharcoal() {
        super(Material.WOOD);
        setUnlocalizedName(Main.MODID + ":charcoal");
        setRegistryName("charcoal");
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(0.2f);
    }
	
	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
}