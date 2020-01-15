package com.focamacho.mysticalcreations.util.handlers;

import java.io.File;
import java.util.Map;

import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.blocks.BlockBase;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.config.ModConfig;
import com.focamacho.mysticalcreations.init.ModBlocks;
import com.focamacho.mysticalcreations.init.ModItems;
import com.focamacho.mysticalcreations.items.ItemEssence;
import com.focamacho.mysticalcreations.items.ItemSeed;
import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.lib.CustomSeeds;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture.Mapper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
		for(CustomSeed seed : CustomSeeds.allSeeds) {
			MysticalCreations.proxy.registerItemColorHandler(seed.getSeed(), seed.getColor(), 0);
			MysticalCreations.proxy.registerItemColorHandler(seed.getEssence(), seed.getColor(), 0);
		}
	}
	
	public static void postInitRegistries(FMLPostInitializationEvent event) {
		
	}
}
