package com.curiouslyodd.intricacies.items;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.items.resources.ItemPlantFibers;
import com.curiouslyodd.intricacies.items.resources.ItemRock;
import com.curiouslyodd.intricacies.items.tools.ItemFlintAxe;
import com.curiouslyodd.intricacies.items.tools.ItemMatch;
import com.curiouslyodd.intricacies.items.weapons.ItemStaff;

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
	
	// Misc/Helpful
	@GameRegistry.ObjectHolder(Main.MODID + ":apple_of_resetting")
    public static ItemAppleOfResetting itemAppleOfResetting;
	
	@GameRegistry.ObjectHolder(Main.MODID + ":book_of_skills")
    public static ItemBookOfSkills itemBookOfSkills;
	
	// Resources
	@GameRegistry.ObjectHolder(Main.MODID + ":plant_fibers")
    public static ItemPlantFibers itemPlantFibers;
	
	@GameRegistry.ObjectHolder(Main.MODID + ":rock")
    public static ItemRock itemRock;
	
	// Tools
	@GameRegistry.ObjectHolder(Main.MODID + ":flint_axe")
    public static ItemFlintAxe itemFlintAxe;
	
	@GameRegistry.ObjectHolder(Main.MODID + ":match")
    public static ItemMatch itemMatch;
	
	// Weapons
	@GameRegistry.ObjectHolder(Main.MODID + ":staff")
    public static ItemStaff itemStaff;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        itemAppleOfResetting.initModel();
        itemBookOfSkills.initModel();
        itemFlintAxe.initModel();
        itemPlantFibers.initModel();
        itemRock.initModel();
        itemMatch.initModel();
        itemStaff.initModel();
    }
	
	public static void registerItems(RegistryEvent.Register<Item> event) {
		// Misc/Helpful
		event.getRegistry().register(new ItemAppleOfResetting(0, 0, false));
    	event.getRegistry().register(new ItemBookOfSkills());
    	
    	// Resources
    	event.getRegistry().register(new ItemPlantFibers());
    	event.getRegistry().register(new ItemRock());
    	
    	// Tools
    	event.getRegistry().register(new ItemFlintAxe(MATERIAL_FLINT));
    	event.getRegistry().register(new ItemMatch());
    	
    	// Weapons
    	event.getRegistry().register(new ItemStaff());
	}
	
}