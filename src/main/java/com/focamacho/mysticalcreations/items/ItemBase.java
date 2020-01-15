package com.focamacho.mysticalcreations.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.init.ModItems;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
