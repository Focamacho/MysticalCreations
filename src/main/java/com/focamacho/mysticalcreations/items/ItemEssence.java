package com.focamacho.mysticalcreations.items;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;

public class ItemEssence extends Item implements IHasModel {
	
	private static int color;
	private String name;
	
	public ItemEssence(String name, int color) {
		this.setRegistryName(name + "_essence");
		this.setUnlocalizedName(name + "_essence");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.color = color;
		this.name = name;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String nameFinal = "";
		nameFinal += I18n.translateToLocal("tile.mysticalcreations.essence.name.before");
		String[] name = this.name.split("_");
		if(name.length > 1) {
			for(String string : name) {
				nameFinal += string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
			}
		} else {
			nameFinal = name[0].substring(0, 1).toUpperCase() + name[0].substring(1) + " ";
		}
		nameFinal += I18n.translateToLocal("item.mysticalcreations.essence.name");
		return nameFinal;
	}
	
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("mysticalcreations:base_essence", "inventory"));
	}

}
