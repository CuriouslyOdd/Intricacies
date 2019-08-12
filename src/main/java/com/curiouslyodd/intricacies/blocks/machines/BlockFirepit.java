package com.curiouslyodd.intricacies.blocks.machines;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.tileentities.machines.TileEntityFirepit;
import com.curiouslyodd.intricacies.tileentities.machines.TileEntityFirepitTESR;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFirepit extends Block implements ITileEntityProvider {
	
	public BlockFirepit() {
        super(Material.ROCK);
        setUnlocalizedName(Main.MODID + ":firepit");
        setRegistryName("firepit");
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(0.2f);
    }
	
	/**
	 * OnBlockActivated
	 * 
	 * When the block is right clicked in the world, for adding
	 * or removing input and output stacks from their respective slots.
	 * 
	 */
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityFirepit te = (TileEntityFirepit) world.getTileEntity(pos);
            
            // If there's output, prioritise collecting that.
            if(!te.getStackOut().isEmpty()) {
            	retrieveOutput(te, player, world, pos);
            	return true;
            }
            
            // Check if we're currently holding an item.
            if(!player.getHeldItem(hand).isEmpty()) {
            	// Check if we're holding an item equal to the one in the input slot.
            	if(player.getHeldItem(hand).isItemEqual(te.getStack())) {
            		// Add our hand stack to the input stack.
                	setInputStackFromHand(player, hand, te);
            	}
            	
            	// The stack we're holding doesn't match input.
            	else {
            		// If there's an input
            		if(!te.getStack().isEmpty()) {
            			// Retrieve the input.
            			retrieveInput(te, player, world, pos);
            		}
            		
            		// We have neither an input or an output, so input
            		else {
            			// Add to that stack.
                		setInputStackFromHand(player, hand, te);
            		}
            	}
            }

            else if(!te.getStack().isEmpty()) {
        		// Retrieve the input.
        		retrieveInput(te, player, world, pos);
            }
        }

        return true;
    }
	
	/**
	 * SetInputStackFromHand
	 * 
	 * Here we add the current held stack to the current input stack
	 * accounting for max stack sizes.
	 * 
	 * @param player
	 * @param hand
	 * @param tileEntity
	 */
	public void setInputStackFromHand(EntityPlayer player, EnumHand hand, TileEntityFirepit tileEntity) {
		int maxSize = tileEntity.getStack().getMaxStackSize();
    	int inputStackSize = tileEntity.getStack().getCount();
    	int handStackSize = player.getHeldItem(hand).getCount();
    	
    	// Check if it will "overflow"
    	if((inputStackSize + handStackSize) > maxSize) {
    		// How much stays in our hand?
    		int retainSize = (inputStackSize + handStackSize) - maxSize;
    		
    		// Change our hand and input stacks to reflect that.
    		tileEntity.setStack(new ItemStack(player.getHeldItem(hand).getItem(), maxSize));
    		player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(player.getHeldItem(hand).getItem(), retainSize));
    	}
    	
    	// It won't overflow
    	else {
    		tileEntity.setStack(player.getHeldItem(hand));
    		player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
            player.openContainer.detectAndSendChanges();
    	}
	}
	
	/**
	 * RetrieveInput
	 * 
	 * Takes the stack out of the input slot.
	 * 
	 * @param tileEntity
	 * @param player
	 * @param world
	 * @param pos
	 */
	public void retrieveInput(TileEntityFirepit tileEntity, EntityPlayer player, World world, BlockPos pos) {
		ItemStack stack = tileEntity.getStack();
        tileEntity.setStack(ItemStack.EMPTY);
        if (!player.inventory.addItemStackToInventory(stack)) {
            EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
            world.spawnEntity(entityItem);
        } else {
            player.openContainer.detectAndSendChanges();
        }
	}
	
	/**
	 * RetrieveOutput
	 * 
	 * Takes the stack out of the output slot.
	 * 
	 * @param tileEntity
	 * @param player
	 * @param world
	 * @param pos
	 */
	public void retrieveOutput(TileEntityFirepit tileEntity, EntityPlayer player, World world, BlockPos pos) {
		ItemStack stack = tileEntity.getStackOut();
        tileEntity.setStackOut(ItemStack.EMPTY);
        if (!player.inventory.addItemStackToInventory(stack)) {
            EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
            world.spawnEntity(entityItem);
        } else {
            player.openContainer.detectAndSendChanges();
        }
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		TileEntityFirepit tileEntity = (TileEntityFirepit) worldIn.getTileEntity(pos);
		tileEntity.isActive = (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockFire) ? true : false;
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFirepit();
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFirepit.class, new TileEntityFirepitTESR());
	}
}
