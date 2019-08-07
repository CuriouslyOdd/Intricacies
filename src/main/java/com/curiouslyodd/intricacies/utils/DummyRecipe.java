package com.curiouslyodd.intricacies.utils;

import java.util.Map.Entry;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class DummyRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private final ItemStack output;

    public DummyRecipe(ItemStack iRecipe) {
        this.output = iRecipe;
    }

    public static IRecipe from(Entry<ResourceLocation, IRecipe> recipe) {
        return new DummyRecipe(recipe.getValue().getRecipeOutput()).setRegistryName(recipe.getKey());
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }
}