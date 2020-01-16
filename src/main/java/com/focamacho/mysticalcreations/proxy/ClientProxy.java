package com.focamacho.mysticalcreations.proxy;

import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.util.handlers.ItemColorHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
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
	
	public void setCropResourceLocation(Block block) {
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("mysticalcreations:base_crop", "age=" + state.getValue(BlockCrop.AGE));
	         	}
		});
	}
	
	public void setItemResourceLocation(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
	}
	
	
}
