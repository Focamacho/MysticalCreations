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
		config.addCustomCategoryComment(category, "Custom seeds creator");
		CUSTOM_SEED_LIST = config.get(category, "CUSTOM_SEED_LIST", new String[] {"base,1,FF8F00", "foda,2,f70202", "brabo,3,00fbff", "tenso,4,c602ed", "indignado,5,00ff0d", "brabissimo,6,eeff00"}).getStringList();
		
		if(config.hasChanged()){
			config.save();
		}
	}
} 
