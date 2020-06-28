package com.focamacho.mysticalcreations.util.handlers;

import com.blakebr0.mysticalagriculture.crafting.ReprocessorManager;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.compat.immersiveengineering.CompatImmersive;
import com.focamacho.mysticalcreations.compat.mysticalagradditions.CompatMysticalAgradditions;
import com.focamacho.mysticalcreations.config.ModConfig;
import com.focamacho.mysticalcreations.init.ModBlocks;
import com.focamacho.mysticalcreations.init.ModItems;
import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.lib.CustomSeeds;
import com.focamacho.mysticalcreations.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ModItems.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		for(Block block : ModBlocks.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries(FMLPreInitializationEvent event) {
		ModConfig.init(new File(event.getModConfigurationDirectory(), "mysticalcreations.cfg"));
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		CustomSeeds.init();
	}
	
	public static void initRegistries(FMLInitializationEvent event) {
		if(Loader.isModLoaded("immersiveengineering") && ModConfig.IMMERSIVE_CLOCHE) CompatImmersive.init();
		FMLInterModComms.sendMessage("waila", "register", "com.focamacho.mysticalcreations.compat.waila.WailaDataProvider.callbackRegister");
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
		MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
		if(Loader.isModLoaded("mysticalagradditions")) CompatMysticalAgradditions.init();
		if(com.blakebr0.mysticalagriculture.config.ModConfig.confMysticalFertilizer) MinecraftForge.EVENT_BUS.register(new FertilizerHandler());
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event) {
		
	}
}
