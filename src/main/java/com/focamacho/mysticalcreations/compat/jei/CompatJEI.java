package com.focamacho.mysticalcreations.compat.jei;

import com.blakebr0.mysticalagriculture.items.ModItems;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CompatJEI implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new CruxCropCategory(helper));
	}

	@Override
	public void register(IModRegistry registry) {
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		registry.addRecipeCatalyst(new ItemStack(ModItems.itemCrafting, 1, 16), CruxCropCategory.UID);
		registry.handleRecipes(CruxCropWrapper.class, recipe -> recipe.setHelper(jeiHelpers), CruxCropCategory.UID);
		registry.addRecipes(CruxCropRecipeMaker.getRecipes(), CruxCropCategory.UID);

	}
}

