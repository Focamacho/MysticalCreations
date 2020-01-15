package com.focamacho.mysticalcreations.lib;

import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.init.ModBlocks;
import com.focamacho.mysticalcreations.init.ModItems;
import com.focamacho.mysticalcreations.items.ItemBase;
import com.focamacho.mysticalcreations.items.ItemEssence;
import com.focamacho.mysticalcreations.items.ItemSeed;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class CustomSeed {

	private BlockCrop crop;
	private Item itemCrop;
	private ItemEssence essence;
	private ItemSeed seed;
	private int tier;
	private int color;
	
	public CustomSeed(String name, Integer tier, Integer color) {
		BlockCrop crop = new BlockCrop(name);
		ItemEssence essence = new ItemEssence(name, color);
		ItemSeed seed = new ItemSeed(name, crop, tier, color);
		crop.setSeed(seed);
		crop.setEssence(essence);
		this.itemCrop = new ItemBlock(crop).setRegistryName(crop.getRegistryName());
		ModBlocks.BLOCKS.add(crop);
		ModItems.ITEMS.add(itemCrop);
		ModItems.ITEMS.add(essence);
		ModItems.ITEMS.add(seed);
		this.crop = crop;
		this.essence = essence;
		this.seed = seed;
		this.tier = tier;
		this.color = color;
		CustomSeeds.allSeeds.add(this);
	}
	
	public int getTier() {
		return this.tier;
	}
	
	public BlockCrop getCrop() {
		return this.crop;
	}
	
	public ItemSeed getSeed() {
		return this.seed;
	}
	
	public ItemEssence getEssence() {
		return this.essence;
	}

	public int getColor() {
		return this.color;
	}

	public Item getItemCrop() {
		return this.itemCrop;
	}
}
