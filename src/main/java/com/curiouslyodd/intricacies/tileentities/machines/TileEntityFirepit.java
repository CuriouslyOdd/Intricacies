package com.curiouslyodd.intricacies.tileentities.machines;

import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityFirepit extends TileEntity implements ITickable {

	private int delayAmount = 20 * 5; // 5 seconds (20 ticks per second)
	private int delayCounter = delayAmount;
	public Boolean isActive = null;
	private ItemStack itemStack = ItemStack.EMPTY;
	private ItemStack itemStackOut = ItemStack.EMPTY;

	@Override
	public void update() {
		// isRemote, do nothing.
		if(this.world.isRemote) return;
		
		// Set isActive if it's null (entity just placed or world just loaded).
		if(isActive == null) this.isActive = (this.world.getBlockState(getPos().down()).getBlock() instanceof BlockFire) ? true : false;
		
		// If we're not active, do nothing.
		if(!isActive) return;
		
		// If the item stack is empty we reset the delay and do nothing.
		if(itemStack.isEmpty()) {
			delayCounter = delayAmount;
		}
		
		// Otherwise we start the delay and process the item.
		else {
			delayCounter--; // Decrement the delay.
			
			// If the delay is done.
			if(delayCounter <= 0) {
				doCook();
				delayCounter = delayAmount; // Reset the delay count.
			}
		}
	}
	
	public void doCook() {
		Item item = itemStack.getItem();
		ItemStack returnStack = ItemStack.EMPTY;
		ItemStack startStack = getStack();
		
		if(item instanceof ItemFood) {
			System.out.println(item.getUnlocalizedName());
			
			switch(item.getUnlocalizedName()) {
				case "item.beefRaw":
					returnStack = new ItemStack(Items.COOKED_BEEF, 1);
				break;
			}
			
			// Shrink and set itemstack.
			startStack.shrink(1);
			setStack(startStack);
			
			// Add the result to the output stack.
			setStackOut(returnStack);
		}
	}
	
	public ItemStack getStack() {
        return itemStack;
    }
	
	public ItemStack getStackOut() {
		return itemStackOut;
	}

    public void setStack(ItemStack stack) {
        this.itemStack = stack;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }
    
    public void setStackOut(ItemStack stack) {
    	if(this.itemStackOut.isEmpty()) {
    		this.itemStackOut = stack;
    	} else {
    		if(this.itemStackOut.isItemEqual(stack)) {
    			this.itemStackOut.setCount(this.itemStackOut.getCount() + stack.getCount());
    		}
    	}
    	
    	markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }
	
	@Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("item")) {
            itemStack = new ItemStack(compound.getCompoundTag("item"));
            itemStackOut = new ItemStack(compound.getCompoundTag("item_out"));
        } else {
            itemStack = ItemStack.EMPTY;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!itemStack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            compound.setTag("item", tagCompound);
            
            NBTTagCompound tagCompoundOut = new NBTTagCompound();
            itemStackOut.writeToNBT(tagCompoundOut);
            compound.setTag("item_out", tagCompoundOut);
        }
        
        return compound;
    }
	
}