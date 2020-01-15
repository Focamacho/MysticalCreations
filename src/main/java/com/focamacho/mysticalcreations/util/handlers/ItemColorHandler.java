package com.focamacho.mysticalcreations.util.handlers;

import com.focamacho.mysticalcreations.blocks.BlockCrop;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ItemColorHandler implements IItemColor {

	private int tintIndex;
	private int color;
	
	public ItemColorHandler(Integer color, int tintIndex) {
		this.color = color;
		this.tintIndex = tintIndex;
	}
	
	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		return this.color;
	}

}
