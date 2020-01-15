package com.focamacho.mysticalcreations.blocks;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;

public class BlockBase extends Block implements IHasModel {
	
	public BlockBase(String name, Material material, SoundType sound, float hardness, float resistance) {
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.setSoundType(sound);
		this.setHardness(hardness);
		this.setResistance(resistance);
	}
	
	@Override
	public void registerModels() {
		MysticalCreations.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	
}