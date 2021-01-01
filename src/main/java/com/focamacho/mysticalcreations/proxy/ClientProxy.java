package com.focamacho.mysticalcreations.proxy;

import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.seeds.CustomSeed;
import com.focamacho.mysticalcreations.util.Reference;
import com.focamacho.mysticalcreations.util.handlers.ItemColorHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemColorHandler(Item item, int color, int tintIndex) {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorHandler(color, tintIndex), item);
	}

	@Override
	public void registerBlockColorHandler(CustomSeed seed) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, blockAccess, pos, tintIndex) -> {
			if (blockAccess == null || pos == null)
				return -1;
			return seed.getColor();
		}, seed.getCrop());

	}

	@Override
	public void setCropResourceLocation(Block block) {
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "base_crop"), "age=" + state.getValue(BlockCrop.AGE));
			}
		});
	}

	@Override
	public void setItemResourceLocation(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
	}

}
