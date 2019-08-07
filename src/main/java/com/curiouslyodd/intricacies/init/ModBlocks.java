package com.curiouslyodd.intricacies.init;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.blocks.FirstBlock;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	@GameRegistry.ObjectHolder(Main.MODID + ":firstblock")
	public static FirstBlock firstBlock;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		firstBlock.initModel();
	}
}