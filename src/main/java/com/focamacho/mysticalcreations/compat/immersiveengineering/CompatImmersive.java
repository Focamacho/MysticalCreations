package com.focamacho.mysticalcreations.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.lib.CustomSeeds;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompatImmersive {

	public static void init() {
		for(CustomSeed seed : CustomSeeds.allSeeds) {
			BelljarHandler.cropHandler.register(new ItemStack(seed.getSeed(),1), new ItemStack[]{new ItemStack(seed.getEssence(),1)}, seed.getCrux() == null ? Item.getItemFromBlock(Blocks.DIRT) : seed.getCrux(), seed.getCrop().getDefaultState());
		}
	}
}