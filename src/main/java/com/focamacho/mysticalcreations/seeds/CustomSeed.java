package com.focamacho.mysticalcreations.seeds;

import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.items.ItemChunk;
import com.focamacho.mysticalcreations.items.ItemEssence;
import com.focamacho.mysticalcreations.items.ItemSeed;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class CustomSeed {

	private final BlockCrop crop;
	private final Item itemCrop;
	private final ItemEssence essence;
	private final ItemSeed seed;
	private final String name;
	private final int tier;
	private final int color;
	private final ItemStack crux;
	private final ItemChunk chunk;
	private final List<ResourceLocation> entities;
	
	public CustomSeed(String name, Integer tier, Integer color, @Nullable ItemStack crux, @Nullable List<ResourceLocation> entities) {
		BlockCrop crop = new BlockCrop(name, crux, tier);
		ItemEssence essence = new ItemEssence(name, color);
		ItemSeed seed = new ItemSeed(name, crop, tier, color);
		ItemChunk chunk = new ItemChunk(name, tier, color);

		crop.setSeed(seed);
		crop.setEssence(essence);

		this.crop = crop;
		this.itemCrop = crop.getItemCrop();
		this.name = name;
		this.essence = essence;
		this.seed = seed;
		this.tier = tier;
		this.color = color;
		this.entities = entities;
		this.crux = crux;

		MysticalCreations.blocks.add(crop);
		MysticalCreations.items.add(itemCrop);
		MysticalCreations.items.add(essence);
		MysticalCreations.items.add(seed);

		if(entities != null) MysticalCreations.items.add(chunk);

		if(entities != null) {
			this.chunk = chunk;
			CustomSeeds.chunkSeeds.add(this);
		} else this.chunk = null;

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
	
	public String getName() {
		return this.name;
	}
	
	public ItemStack getCrux() {
		return this.crux;
	}
	
	public ItemChunk getChunk() {
		return this.chunk;
	}
	
	public List<ResourceLocation> getEntities() {
		return this.entities;
	}
}
