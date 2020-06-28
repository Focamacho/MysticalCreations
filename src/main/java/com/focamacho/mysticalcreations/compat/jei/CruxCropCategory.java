package com.focamacho.mysticalcreations.compat.jei;

import com.blakebr0.cucumber.helper.ResourceHelper;
import com.focamacho.mysticalcreations.util.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

import java.util.List;

public class CruxCropCategory implements IRecipeCategory<CruxCropWrapper> {
	
    public static final String UID = "mysticalcreations:crux_crop_jei";

    private IDrawable background;

    public CruxCropCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(ResourceHelper.getResource(Reference.MOD_ID, "textures/gui/crux_crop_jei.png"), 0, 0, 136, 65);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return I18n.translateToLocal("jei.crux_crop");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }
    
    @Override
    public void setRecipe(IRecipeLayout layout, CruxCropWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup group = layout.getItemStacks();
        
        List<List<ItemStack>> inputs = ingredients.getInputs(ItemStack.class);
        List<ItemStack> outputs = ingredients.getOutputs(ItemStack.class).get(0);

        group.init(0, true, 0, 24);
        group.init(1, true, 55, 0);
        group.init(2, true, 55, 47);
        group.init(3, true, 55, 23);
        group.init(4, false, 114, 24);
        
        group.set(0, inputs.get(0));
        group.set(1, inputs.get(1));
        group.set(2, inputs.get(2));
        group.set(3, inputs.get(3));
        group.set(4, outputs);
    }

	@Override
	public String getModName() {
		return Reference.NAME;
	}
}