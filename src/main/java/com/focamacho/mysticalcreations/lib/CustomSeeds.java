package com.focamacho.mysticalcreations.lib;

import java.util.ArrayList;
import java.util.List;

import com.focamacho.mysticalcreations.config.ModConfig;

public class CustomSeeds {

	public static List<CustomSeed> allSeeds = new ArrayList<CustomSeed>();
	
	public static void init() {
		for(String config : ModConfig.CUSTOM_SEED_LIST) {
			String[] split = config.split(",");
			new CustomSeed(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2], 16));
		}
	}
}
