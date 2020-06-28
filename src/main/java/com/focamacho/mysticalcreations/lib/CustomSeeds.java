package com.focamacho.mysticalcreations.lib;

import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CustomSeeds {

	public static List<CustomSeed> allSeeds = new ArrayList<CustomSeed>();
	public static List<CustomSeed> chunkSeeds = new ArrayList<CustomSeed>();
	
	public static void init() {
		for(String config : ModConfig.CUSTOM_SEED_LIST) {
			createSeedByString(config);
		}
	}
	
	public static void createSeedByString(String string) {
		String config = string;
		String[] split = config.split(";");
		if(split.length != 5) {
			MysticalCreations.logger.error("Invalid Config String Length!");
			MysticalCreations.logger.error("Skipping line: " + config);
			return;
		}
		String name = split[0];
		name.replaceAll(" ", "_");
		Integer tier;
		try {
			tier = Integer.parseInt(split[1]);
		} catch (NumberFormatException e) {
			MysticalCreations.logger.error("Invalid Config! Invalid tier number!");
			MysticalCreations.logger.error("Skipping line: " + config);
			return;
		}
		Integer color;
		try {
			color = Integer.parseInt(split[2], 16);
		} catch (NumberFormatException e){
			MysticalCreations.logger.error("Invalid Config! Invalid color!");
			MysticalCreations.logger.error("Skipping line: " + config);
			return;
		}
		ItemStack crux;
		if(!split[3].contains("null")) {
			try {
				String[] splitCrux = split[3].split(":");
				if(splitCrux.length > 2) {
					crux = new ItemStack(Block.getBlockFromName(splitCrux[0] + ":" + splitCrux[1]), 1, Integer.parseInt(splitCrux[2]));
				} else {
					crux = new ItemStack(Block.getBlockFromName(splitCrux[0] + ":" + splitCrux[1]), 1, 0);
				}
				if(crux == null || crux.getItem() == Items.AIR) {
					MysticalCreations.logger.error("Invalid Config! Invalid Crux!");
					MysticalCreations.logger.error("Skipping line: " + config);
					return;
				}
			} catch (Exception e) {
				MysticalCreations.logger.error("Invalid Config! Invalid Crux!");
				MysticalCreations.logger.error("Skipping line: " + config);
				return;
			}
		} else crux = null;
		
		List<ResourceLocation> entities = new ArrayList<>();
		if(!split[4].contains("null")) {
			try {
				String[] entitiesConfigs = split[4].split(",");
				for(String entity : entitiesConfigs) {
					entities.add(new ResourceLocation(entity));
				}
			} catch (Exception e) {
				MysticalCreations.logger.error("Invalid Config! Invalid Entity!");
				MysticalCreations.logger.error("Skipping line: " + config);
				return;
			}
		} else entities = null;
		
		new CustomSeed(name, tier, color, crux, entities);
	}
}
