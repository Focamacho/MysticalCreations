package com.focamacho.mysticalcreations.util.handlers;

import com.blakebr0.mysticalagriculture.items.ModItems;
import com.focamacho.mysticalcreations.blocks.BlockCrop;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FertilizerHandler {

	@SubscribeEvent
	public void onRightClickCrop(RightClickBlock event) {
		System.out.println(event.getWorld().getBlockState(event.getPos()).getBlock().getClass());
		if(event.getEntityPlayer() == null) return;
		Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
		if(block instanceof BlockCrop) {
			ItemStack item = event.getItemStack();
			if(item.getItem().equals(ModItems.itemMysticalFertilizer)) {
				BlockCrop crop = (BlockCrop) block;
				IBlockState state = event.getWorld().getBlockState(event.getPos());

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
			    	   ItemDye.spawnBonemealParticles(event.getWorld(), event.getPos(), 10);
			       }
			       event.getItemStack().shrink(1);
			    }
			}
		}
	}
}