package com.curiouslyodd.intricacies.items.tools;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFlintAxe extends ItemAxe {
	
	public ItemFlintAxe(ToolMaterial material) {
		super(material, material.getAttackDamage(), material.getEfficiency());
		setUnlocalizedName(Main.MODID + ":flint_axe");
		setRegistryName("flint_axe");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
    	Material material = state.getMaterial();
    	
    	// If the material isn't wood/grass/leaves we can't break it.
    	return (material == Material.WOOD || material == Material.GRASS || material == Material.LEAVES || material == Material.VINE) ? this.efficiency : 0;
    }

	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
}