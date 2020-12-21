package com.focamacho.mysticalcreations;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.compat.immersiveengineering.CompatImmersive;
import com.focamacho.mysticalcreations.compat.mysticalagradditions.CompatMysticalAgradditions;
import com.focamacho.mysticalcreations.config.ModConfig;
import com.focamacho.mysticalcreations.proxy.CommonProxy;
import com.focamacho.mysticalcreations.seeds.CustomSeed;
import com.focamacho.mysticalcreations.seeds.CustomSeeds;
import com.focamacho.mysticalcreations.util.Reference;
import com.focamacho.mysticalcreations.util.handlers.FertilizerHandler;
import com.focamacho.mysticalcreations.util.handlers.MobDropsHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Register Events
		MinecraftForge.EVENT_BUS.register(this);

		//Logger
		logger = event.getModLog();

		//Init config
		ModConfig.init(new File(event.getModConfigurationDirectory(), "mysticalcreations.cfg"));

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		//Garden Cloche Compat
		if(Loader.isModLoaded("immersiveengineering") && ModConfig.IMMERSIVE_CLOCHE) CompatImmersive.init();

		//Waila Compat
		FMLInterModComms.sendMessage("waila", "register", "com.focamacho.mysticalcreations.compat.waila.WailaDataProvider.callbackRegister");

		//Mystical Agradditions Compat
		if(Loader.isModLoaded("mysticalagradditions")) CompatMysticalAgradditions.init();

		//Mystical Agriculture Fertilizer Compat
		if(com.blakebr0.mysticalagriculture.config.ModConfig.confMysticalFertilizer) MinecraftForge.EVENT_BUS.register(new FertilizerHandler());

		//Register Custom Seeds Colors
		for(CustomSeed seed : CustomSeeds.allSeeds) {
			MysticalCreations.proxy.registerItemColorHandler(seed.getSeed(), seed.getColor(), 0);
			MysticalCreations.proxy.registerItemColorHandler(seed.getEssence(), seed.getColor(), 0);
			MysticalCreations.proxy.registerItemColorHandler(seed.getItemCrop(), seed.getColor(), 1);
			MysticalCreations.proxy.registerBlockColorHandler(seed);
			if(seed.getChunk() != null) MysticalCreations.proxy.registerItemColorHandler(seed.getChunk(), seed.getColor(), 0);

			//Reprocessor Recipes
			if(com.blakebr0.mysticalagriculture.config.ModConfig.confSeedReprocessor) {
				ReprocessorManager.addRecipe(new ItemStack(seed.getEssence(), 2), new ItemStack(seed.getSeed()));
			}
		}

		//Mob Chunks Handler
		MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
	}

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		CustomSeeds.init();

		MysticalCreations.items.forEach(event.getRegistry()::register);
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		MysticalCreations.blocks.forEach(event.getRegistry()::register);
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		items.forEach(item -> {
			if(item instanceof BlockCrop.ItemCrop) MysticalCreations.proxy.setItemResourceLocation(item, "mysticalcreations:base_crop");
		});

		blocks.forEach(block -> {
			if(block instanceof BlockCrop) MysticalCreations.proxy.setCropResourceLocation(block);
		});
	}

}
