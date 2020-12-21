package com.focamacho.mysticalcreations.compat.mysticalagradditions;

import com.blakebr0.mysticalagradditions.config.ModConfig;

public class CompatMysticalAgradditions {

	public static boolean fertilizerTier6 = false;

	public static void init() {
		fertilizerTier6 = ModConfig.confFertilizableCrops;
	}
	
}
