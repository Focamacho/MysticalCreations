package com.focamacho.mysticalcreations.config;

import java.io.File;

import com.focamacho.mysticalcreations.util.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModConfig {
	
	public static Configuration config;
	public static ModConfig instance;
	
	public static String[] CUSTOM_SEED_LIST;
	
	@SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if(eventArgs.getModID().equals(Reference.MOD_ID)) {
            ModConfig.syncConfig();
        }
    }
	
	public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
	}
	
	public static void syncConfig() {
	
		String category;
		
		category = "Custom Seeds";
		config.addCustomCategoryComment(category, "Here you can create your own seeds. It generates for you the Seed, Essence, Crop and Mob Chunk.\n"
												+ "For making the recipes you'll need to use CraftTweaker!\n"
												+ "- Syntax: name;tier;color;crux;entity\n"
												+ "- 'name' should be lower case with underscores for spaces.\n"
												+ "- 'tier' should be 1, 2, 3, 4, 5 or 6.\n"
												+ "- 'color' the color of the singularity as a hex value. http://htmlcolorcodes.com/\n"
												+ "- 'crux' can be any block, leave this as null if you don't want to use crux\n"
												+ "Example: minecraft:diamond_block\n"
												+ "- 'entity' can be any entity, leave this as null if you don't want mob chunks\n"
												+ "Example: minecraft:pig\n"
												+ "\n"
												+ "Example for Cake Seeds:\n"
												+ "cake;2;724C1B;null;null");
		CUSTOM_SEED_LIST = config.get(category, "CUSTOM_SEED_LIST", new String[] {"cake;2;724C1B;null;null","witch;4;2E0365;null;minecraft:witch"}).getStringList();
		
		if(config.hasChanged()){
			config.save();
		}
	}
} 
