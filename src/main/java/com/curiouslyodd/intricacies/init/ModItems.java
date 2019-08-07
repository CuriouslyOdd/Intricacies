package com.curiouslyodd.intricacies.init;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.items.ItemAppleOfResetting;
import com.curiouslyodd.intricacies.items.ItemBookOfSkills;
import com.curiouslyodd.intricacies.items.tools.ItemFlintAxe;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	// Materials
	public static ToolMaterial MATERIAL_FLINT = EnumHelper.addToolMaterial("material_flint", 0, 59, 2.0F, 0.0F, 15);
	
	// Items
	@GameRegistry.ObjectHolder(Main.MODID + ":apple_of_resetting")
    public static ItemAppleOfResetting itemAppleOfResetting;
	
	@GameRegistry.ObjectHolder(Main.MODID + ":book_of_skills")
    public static ItemBookOfSkills itemBookOfSkills;
	
	@GameRegistry.ObjectHolder(Main.MODID + ":flint_axe")
    public static ItemFlintAxe itemFlintAxe;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        itemAppleOfResetting.initModel();
        itemBookOfSkills.initModel();
        itemFlintAxe.initModel();
    }
	
	public static void initItems(RegistryEvent.Register<Item> event) {
		// Items
		event.getRegistry().register(new ItemAppleOfResetting(0, 0, false));
    	event.getRegistry().register(new ItemBookOfSkills());
    	
    	// Tools
    	event.getRegistry().register(new ItemFlintAxe(MATERIAL_FLINT));
	}
	
}