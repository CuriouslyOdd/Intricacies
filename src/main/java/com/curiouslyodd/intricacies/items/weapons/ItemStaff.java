package com.curiouslyodd.intricacies.items.weapons;

import javax.annotation.Nonnull;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStaff extends ItemBow {

	public ItemStaff() {
		super();
		setUnlocalizedName(Main.MODID + ":staff");
		setRegistryName("staff");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstackIn = playerIn.getHeldItem(handIn);
		
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstackIn, worldIn, playerIn, handIn, true);
	    if(ret != null) {
	    	return ret;
	    }
		
		playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstackIn);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
	    EntityPlayer player = (EntityPlayer) entityLiving;
	    ItemStack ammo = new ItemStack(Items.ARROW);
	    int useTime = this.getMaxItemUseDuration(stack) - timeLeft;
	    useTime = ForgeEventFactory.onArrowLoose(stack, worldIn, player, useTime, !ammo.isEmpty());

	    if(useTime < 5) {
	      return;
	    }

	    shootProjectile(ammo, stack, worldIn, player, useTime);
	}
	
	public void shootProjectile(@Nonnull ItemStack ammoIn, @Nonnull ItemStack bow, World worldIn, EntityPlayer player, int timeLeft) {
	    if(!worldIn.isRemote) {
	    	int i = this.getMaxItemUseDuration(ammoIn) - timeLeft;
	    	float f = getArrowVelocity(i);
	    	ItemArrow itemarrow = (ItemArrow)(ammoIn.getItem() instanceof ItemArrow ? ammoIn.getItem() : Items.ARROW);
	    	EntityArrow entityarrow = itemarrow.createArrow(worldIn, ammoIn, player);
            entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);
            
            worldIn.spawnEntity(entityarrow);
	    }
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
}