package com.focamacho.mysticalcreations.seeds;

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
	
	public static void createSeedByString(String config) {
		String[] split = config.split(";");

		//Check if contains all arguments
		if(split.length != 5) {
			MysticalCreations.logger.error("Invalid Config String Length!");
			MysticalCreations.logger.error("Skipping line: " + config);
			return;
		}

		//Get name
		String name = split[0];
		name.replace(" ", "_");


		//Get tier
		int tier;
		try {
			tier = Integer.parseInt(split[1]);
		} catch (NumberFormatException e) {
			MysticalCreations.logger.error("Invalid Config! Invalid tier number!");
			MysticalCreations.logger.error("Skipping line: " + config);
			return;
		}

		//Get color
		int color;
		try {
			color = Integer.parseInt(split[2], 16);
		} catch (NumberFormatException e){
			MysticalCreations.logger.error("Invalid Config! Invalid color!");
			MysticalCreations.logger.error("Skipping line: " + config);
			return;
		}

		//Get crux
		ItemStack crux;
		if(!split[3].equals("null")) {
			try {
				String[] splitCrux = split[3].split(":");
				int meta = splitCrux.length > 2 ? Integer.parseInt(splitCrux[2]) : 0;

				crux = new ItemStack(Block.getBlockFromName(splitCrux[0] + ":" + splitCrux[1]), 1, meta);

				if(crux.getItem() == Items.AIR) {
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

		//Get Entities
		List<ResourceLocation> entities = new ArrayList<>();
		if(!split[4].equals("null")) {
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
