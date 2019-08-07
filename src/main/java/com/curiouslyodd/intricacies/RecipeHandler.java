package com.curiouslyodd.intricacies;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.curiouslyodd.intricacies.utils.DummyRecipe;
import com.google.common.collect.Lists;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class RecipeHandler {

	/**
	 * Remove recipes
	 * 
	 * This removes ALL vanilla recipes from the registry.
	 * 
	 */
	public static void removeRecipes() {
		ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>)ForgeRegistries.RECIPES;
		ArrayList<Entry<ResourceLocation, IRecipe>> recipes = Lists.newArrayList(recipeRegistry.getEntries());
		
		for(Entry<ResourceLocation, IRecipe> recipe : recipes) {
			recipeRegistry.remove(recipe.getKey());
			recipeRegistry.register(DummyRecipe.from(recipe));
		}
	}
	
}