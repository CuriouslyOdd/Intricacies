package com.curiouslyodd.intricacies.items.tools;

import java.util.Random;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMatch extends Item {
	public ItemMatch() {
		setUnlocalizedName(Main.MODID + ":match");
		setRegistryName("match");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos firePos = pos.up(); // Block on top.
		ItemStack itemstack = player.getHeldItem(hand);
		
		// If the block above is air.
		if (worldIn.isAirBlock(firePos)) {
			// Random chance to make fire.
			if(new Random().nextInt(5) == 4) {
	            worldIn.setBlockState(firePos, Blocks.FIRE.getDefaultState(), 11); // Add the fire.
	            itemstack.shrink(1); // Remove a match.
			}
        }

		// Play lever sound.
        worldIn.playSound(player, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        return EnumActionResult.SUCCESS;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
