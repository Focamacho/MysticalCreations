package com.focamacho.mysticalcreations.util.handlers;

import com.blakebr0.mysticalagriculture.items.ModItems;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.compat.mysticalagradditions.FertilizableCrops;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FertilizerHandler {

	@SubscribeEvent
	public void onRightClickCrop(RightClickBlock event) {
		if(event.getEntityPlayer() == null) return;
		Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
		if(block instanceof BlockCrop) {
			ItemStack item = event.getItemStack();
			if(item.getItem().equals(ModItems.itemMysticalFertilizer)) {
				BlockCrop crop = (BlockCrop) block;
				IBlockState state = event.getWorld().getBlockState(event.getPos());

				if(crop.getTier() == 6 && !FertilizableCrops.fertilizerTier6) {
					event.setCanceled(true);
					return;
				}
				
			    int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(event.getEntityPlayer(), event.getWorld(), event.getPos(), state, event.getItemStack(), event.getHand());
			    if(hook != 0) {
			    	event.setCanceled(true);
			       	return;
			    }
			    IGrowable growable = (IGrowable)state.getBlock();
		       	if(growable.canGrow(event.getWorld(), event.getPos(), state, event.getWorld().isRemote)){
		           if(!event.getWorld().isRemote){
		        	   event.getWorld().setBlockState(event.getPos(), crop.withAge(crop.getMaxAge()), 2);
			       } else {
			    	   event.getWorld().playEvent(2005, event.getPos(), 0);
			       }
			       event.getItemStack().shrink(1);
			    }
			}
			
			if(item.getItem().equals(ModItems.itemFertilizedEssence)) {
				BlockCrop crop = (BlockCrop) block;
				IBlockState state = event.getWorld().getBlockState(event.getPos());

				if(crop.getTier() == 6 && !FertilizableCrops.fertilizerTier6) {
					event.setCanceled(true);
					return;
				}
				
				int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(event.getEntityPlayer(), event.getWorld(), event.getPos(), state, event.getItemStack(), event.getHand());
		        if(hook != 0) {
		        	event.setCanceled(true);
		        	return;
		        }
		       
		        if(crop.isMaxAge(state)) {
		        	event.setCanceled(true);
		        	return;
		        }
		        
		        if(state.getBlock() instanceof IGrowable){
		            IGrowable growable = (IGrowable)state.getBlock();

		            if(growable.canGrow(event.getWorld(), event.getPos(), state, event.getWorld().isRemote)){
		                if(!event.getWorld().isRemote){
		                    growable.grow(event.getWorld(), event.getWorld().rand, event.getPos(), state);
		                    if(crop.isMaxAge(event.getWorld().getBlockState(event.getPos()))) {
		                    	event.getItemStack().shrink(1);
		                    }
		                }
		            }
		        }
		    }
		}
	}
}