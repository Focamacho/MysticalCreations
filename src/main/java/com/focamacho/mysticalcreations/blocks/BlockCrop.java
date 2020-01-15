package com.focamacho.mysticalcreations.blocks;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringEscapeUtils;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.items.ModItems;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.util.IHasModel;

import it.unimi.dsi.fastutil.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture.Mapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockCrop extends BlockCrops implements IHasModel {
	
    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
    private Item seed;
    private Item crop;
    private Item essence;
    private String name;
    private Block crux;
    
    public BlockCrop(String name, @Nullable Block crux){
		this.setUnlocalizedName(name + "_crop");
		this.setRegistryName(name + "_crop");
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        this.crux = crux;
        this.name = name;
    }
    
    @Override
    public String getLocalizedName() {
    	String nameFinal = "";
    	nameFinal += I18n.translateToLocal("tile.mysticalcreations.crop.name.before");
		String[] name = this.name.split("_");
		if(name.length > 1) {
			for(String string : name) {
				nameFinal += string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
			}
		} else {
			nameFinal = name[0].substring(0, 1).toUpperCase() + name[0].substring(1) + " ";
		}
		nameFinal += I18n.translateToLocal("tile.mysticalcreations.crop.name");
		return nameFinal;
    }
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
    	this.checkAndDropBlock(world, pos, state);
    	int i = this.getAge(state);
        if(world.getLightFromNeighbors(pos.up()) >= 9){
	    	if(i < this.getMaxAge()){
	    		float f = getGrowthChance(this, world, pos);
	    		if(rand.nextInt((int)(35.0F / f) + 1) == 0) {
	    			world.setBlockState(pos, this.withAge(i + 1), 2);
	    		}
	    	}
        }
    }
    
    public ItemCrop getItemCrop() {
    	return new ItemCrop(this, this.name);
    }

	@Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state){
        return true;
    }
	
	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		if (this.crux != null && world.getBlockState(pos.down(2)) != this.crux)
			return;
		
		super.grow(world, rand, pos, state);
	}
	
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
    	return EnumPlantType.Crop;
    }
     
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return CROPS_AABB;
    }
        
    public BlockCrop setSeed(Item seed){
    	this.seed = seed;
    	return this;
    }

    @Override
    public Item getSeed(){
    	return this.seed;
    }
    
    public BlockCrop setEssence(Item essence){
    	this.essence = essence;
    	return this;
    }
    
    public BlockCrop setCrop(Item crop){
    	this.crop = crop;
    	return this;
    }
    
    @Override
    public Item getCrop() {
    	return this.crop;
    }
    
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    	
        int age = state.getValue(AGE);
        Random rand = Utils.rand;

        int essence = 0;
        int fertilizer = 0;
        int seeds = 1;

        if(age == 7){
        	if(ModConfig.confSeedChance > 0){
        		if(rand.nextInt(100 / ModConfig.confSeedChance) > 0){
        			seeds = 1;	
        		} else {
        			seeds = 2;
        		}
        	}
        	else {
        		seeds = 1;
        	}
        }
        
        if(age == 7){
        	if(ModConfig.confFertilizedEssenceChance > 0){
        		if(rand.nextInt(100 / ModConfig.confFertilizedEssenceChance) > 0){
        			fertilizer = 0;
        		} else {
        			fertilizer = 1;
        		}
        	} 
        	else fertilizer = 0;
        }
        
        if(age == 7){
        	if(ModConfig.confEssenceChance > 0){
                if(rand.nextInt(100 / ModConfig.confEssenceChance) > 0){
                	essence = 1;
                } else {
                	essence = 2;
                }           		
        	}
        	else essence = 1;
        }

        drops.add(new ItemStack(this.getSeed(), seeds, 0));
        if(essence > 0){ drops.add(new ItemStack(this.essence, essence, 0)); }
        if(fertilizer > 0 && ModConfig.confFertilizedEssence){ drops.add(new ItemStack(ModItems.itemFertilizedEssence, fertilizer, 0)); }
    }
	
	@Override
	public void registerModels() {
		ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
			@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("mysticalcreations:base_crop", "age=" + state.getValue(BlockCrop.AGE));
	         	}
		});
	}
	
	class ItemCrop extends ItemBlock implements IHasModel {

		private String name;
		
		public ItemCrop(Block block, String name) {
			super(block);
			this.setCreativeTab((CreativeTabs)null);
			this.setRegistryName(name + "_crop");
			this.name = name;
		}
		
		@Override
		public String getItemStackDisplayName(ItemStack stack) {
			String nameFinal = "";
			String[] name = this.name.split("_");
			if(name.length > 1) {
				for(String string : name) {
					nameFinal += string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
				}
			} else {
				nameFinal = name[0].substring(0, 1).toUpperCase() + name[0].substring(1) + " ";
			}
			nameFinal += I18n.translateToLocal("tile.mysticalcreations.crop.name");
			return nameFinal;
		}

		@Override
		public void registerModels() {
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("mysticalcreations:base_crop", "inventory"));
		}
		
	}
}
