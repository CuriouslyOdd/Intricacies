package com.curiouslyodd.intricacies.items;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.capabilities.skills.SkillManager;
import com.curiouslyodd.intricacies.network.OpenStatsMessage;
import com.curiouslyodd.intricacies.network.PacketHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBookOfSkills extends Item {
	
	public ItemBookOfSkills() {
		setUnlocalizedName(Main.MODID + ":book_of_skills");
		setRegistryName("book_of_skills");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		
		if(!worldIn.isRemote) {
			NBTTagCompound stats = SkillManager.buildSkillList(playerIn);
			PacketHandler.INSTANCE.sendTo(new OpenStatsMessage(stats), (EntityPlayerMP) playerIn);
		}
		
	    return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}