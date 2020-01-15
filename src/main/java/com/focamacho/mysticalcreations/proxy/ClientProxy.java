package com.focamacho.mysticalcreations.proxy;

import com.focamacho.mysticalcreations.items.ItemEssence;
import com.focamacho.mysticalcreations.items.ItemSeed;
import com.focamacho.mysticalcreations.util.handlers.ItemColorHandler;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("mysticalcreations:base_crop", id));
	}
	
	public void registerItemColorHandler(Item item, int color, int tintIndex) {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorHandler(color, tintIndex), item);
	}
	
}
