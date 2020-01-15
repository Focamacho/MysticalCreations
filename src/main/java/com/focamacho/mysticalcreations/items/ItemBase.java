package com.focamacho.mysticalcreations.items;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
	}
	
	@Override
	public void registerModels() {
		MysticalCreations.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
