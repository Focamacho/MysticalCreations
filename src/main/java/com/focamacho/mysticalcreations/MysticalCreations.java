package com.focamacho.mysticalcreations;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.focamacho.mysticalcreations.compat.immersiveengineering.CompatImmersive;
import com.focamacho.mysticalcreations.compat.mysticalagradditions.CompatMysticalAgradditions;
import com.focamacho.mysticalcreations.config.ModConfig;
import com.focamacho.mysticalcreations.proxy.CommonProxy;
import com.focamacho.mysticalcreations.seeds.CustomSeed;
import com.focamacho.mysticalcreations.seeds.CustomSeeds;
import com.focamacho.mysticalcreations.util.Reference;
import com.focamacho.mysticalcreations.util.handlers.MobDropsHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class MysticalCreations {

	//Items & Blocks
	public static final List<Item> items = new ArrayList<>();
	public static final List<Block> blocks = new ArrayList<>();

	public static Logger logger;

	@SidedProxy(serverSide = "com.focamacho.mysticalcreations.proxy.CommonProxy", clientSide = "com.focamacho.mysticalcreations.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		//Logger
		logger = event.getModLog();

		//Init config
		ModConfig.init(new File(event.getModConfigurationDirectory(), "mysticalcreations.cfg"));

		//Init Seeds
		CustomSeeds.init();
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		//Waila Compat
		FMLInterModComms.sendMessage("waila", "register", "com.focamacho.mysticalcreations.compat.waila.WailaDataProvider.callbackRegister");

		//Mystical Agradditions Compat
		if(Loader.isModLoaded("mysticalagradditions")) CompatMysticalAgradditions.init();

		//Mystical Agriculture Fertilizer Compat
		//if(com.blakebr0.mysticalagriculture.config.ModConfig.confMysticalFertilizer) MinecraftForge.EVENT_BUS.register(new FertilizerHandler());

		//Register Custom Seeds Colors & Reprocessor Recipes
		for(CustomSeed seed : CustomSeeds.allSeeds) {
			proxy.registerItemColorHandler(seed.getSeed(), seed.getColor(), 0);
			proxy.registerItemColorHandler(seed.getEssence(), seed.getColor(), 0);
			proxy.registerItemColorHandler(seed.getItemCrop(), seed.getColor(), 1);
			proxy.registerBlockColorHandler(seed);
			if(seed.getChunk() != null) proxy.registerItemColorHandler(seed.getChunk(), seed.getColor(), 0);

			//Reprocessor Recipes
			if(com.blakebr0.mysticalagriculture.config.ModConfig.confSeedReprocessor) {
				ReprocessorManager.addRecipe(new ItemStack(seed.getEssence(), 2), new ItemStack(seed.getSeed()));
			}
		}

		//Mob Chunks Handler
		MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		//Set Crux
		for (CustomSeed seed : CustomSeeds.allSeeds) {
			if(!seed.getCrux().equalsIgnoreCase("null")) {
				String cruxString = seed.getCrux();
				try {
					String[] splitCrux = cruxString.split(":");
					int meta = splitCrux.length > 2 ? Integer.parseInt(splitCrux[2]) : 0;

					ItemStack crux = new ItemStack(Block.getBlockFromName(splitCrux[0] + ":" + splitCrux[1]), 1, meta);

					if(crux.getItem() == Items.AIR) {
						MysticalCreations.logger.error("Invalid Config! Invalid Crux!");
						MysticalCreations.logger.error("Crux: " + cruxString);
						seed.setCrux("null");
					} else {
						seed.getCrop().setCrux(crux);
					}
				} catch (Exception e) {
					MysticalCreations.logger.error("Invalid Config! Invalid Crux!");
					MysticalCreations.logger.error("Crux: " + cruxString);
					seed.setCrux("null");
				}
			}
		}

		//Garden Cloche Compat
		if(Loader.isModLoaded("immersiveengineering") && ModConfig.IMMERSIVE_CLOCHE) CompatImmersive.init();
	}

}
