package com.focamacho.mysticalcreations.compat.jei;

import com.focamacho.mysticalcreations.seeds.CustomSeed;
import com.focamacho.mysticalcreations.seeds.CustomSeeds;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CruxCropRecipeMaker {

	public static List<CruxCropWrapper> getRecipes() {
		List<CruxCropWrapper> recipes = new ArrayList<CruxCropWrapper>();

		for (CustomSeed seed : CustomSeeds.allSeeds) {
			if (seed.getCrux() != null) {
				ItemStack input = new ItemStack(seed.getSeed());
				ItemStack crop = new ItemStack(seed.getItemCrop());
				ItemStack crux = seed.getCrux();
				ItemStack output = new ItemStack(seed.getEssence());

				recipes.add(new CruxCropWrapper(input, crop, crux, output));
			}
		}

		return recipes;
	}
}
