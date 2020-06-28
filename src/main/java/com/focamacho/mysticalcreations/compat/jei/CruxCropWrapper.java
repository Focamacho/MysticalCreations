package com.focamacho.mysticalcreations.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CruxCropWrapper implements IRecipeWrapper {

	private IJeiHelpers helper;
	private ItemStack input;
	private ItemStack crop;
	private ItemStack crux;
	private ItemStack output;

	public CruxCropWrapper(ItemStack input, ItemStack crop, ItemStack crux, ItemStack output) {
		this.input = input;
		this.crop = crop;
		this.crux = crux;
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> inputs = new ArrayList<>();
		IStackHelper helper = this.helper.getStackHelper();
		
		inputs.add(helper.toItemStackList(this.input));
		inputs.add(helper.toItemStackList(this.crop));
		inputs.add(helper.toItemStackList(this.crux));
		inputs.add(helper.toItemStackList(new ItemStack(Blocks.FARMLAND)));
		
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, this.output);
	}
	
	public CruxCropWrapper setHelper(IJeiHelpers helper) {
		this.helper = helper;
		return this;
	}
}
