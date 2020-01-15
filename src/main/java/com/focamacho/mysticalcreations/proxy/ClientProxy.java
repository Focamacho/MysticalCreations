package com.focamacho.mysticalcreations.proxy;

import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.util.handlers.ItemColorHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("mysticalcreations:base_crop", id));
	}
	
	public void registerItemColorHandler(Item item, int color, int tintIndex) {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorHandler(color, tintIndex), item);
	}
	
	public void registerBlockColorHandler(CustomSeed seed) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, blockAccess, pos, tintIndex) -> {
            if (blockAccess == null || pos == null)
                return -1;
            return seed.getColor();
        }, seed.getCrop());

	}
	
}
