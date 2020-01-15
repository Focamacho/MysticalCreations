package com.focamacho.mysticalcreations.lib;

import java.util.ArrayList;
import java.util.List;

import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CustomSeeds {

	public static List<CustomSeed> allSeeds = new ArrayList<CustomSeed>();
	public static List<CustomSeed> chunkSeeds = new ArrayList<CustomSeed>();
	
	public static void init() {
		for(String config : ModConfig.CUSTOM_SEED_LIST) {
			String[] split = config.split(";");
			if(split.length != 5) {
				MysticalCreations.logger.error("Invalid Config String Length!");
				MysticalCreations.logger.error("Skipping line: " + config);
				continue;
			}
			String name = split[0];
			name.replaceAll(" ", "_");
			Integer tier;
			try {
				tier = Integer.parseInt(split[1]);
			} catch (NumberFormatException e) {
				MysticalCreations.logger.error("Invalid Config! Invalid tier number!");
				MysticalCreations.logger.error("Skipping line: " + config);
				continue;
			}
			Integer color;
			try {
				color = Integer.parseInt(split[2], 16);
			} catch (NumberFormatException e){
				MysticalCreations.logger.error("Invalid Config! Invalid color!");
				MysticalCreations.logger.error("Skipping line: " + config);
				continue;
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
						continue;
					}
				} catch (Exception e) {
					MysticalCreations.logger.error("Invalid Config! Invalid Crux!");
					MysticalCreations.logger.error("Skipping line: " + config);
					continue;
				}
			} else crux = null;
			
			EntityEntry entity;
			if(!split[4].contains("null")) {
				try {
					entity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(split[4]));
					if(entity == null) {
						MysticalCreations.logger.error("Invalid Config! Invalid Entity!");
						MysticalCreations.logger.error("Skipping line: " + config);
						continue;
					}
				} catch (Exception e) {
					MysticalCreations.logger.error("Invalid Config! Invalid Entity!");
					MysticalCreations.logger.error("Skipping line: " + config);
					continue;
				}
			} else entity = null;
			
			new CustomSeed(name, tier, color, crux, entity);
		}
	}
}
