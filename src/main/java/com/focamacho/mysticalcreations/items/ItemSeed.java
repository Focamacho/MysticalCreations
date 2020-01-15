package com.focamacho.mysticalcreations.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;
import com.blakebr0.mysticalagriculture.lib.BehaviorSeeds;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import com.focamacho.mysticalcreations.init.ModBlocks;
import com.focamacho.mysticalcreations.init.ModItems;
import com.focamacho.mysticalcreations.lib.CustomSeed;
import com.focamacho.mysticalcreations.lib.CustomSeeds;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSeed extends ItemSeeds implements IHasModel {
	
	private static int color;
	private int tier;
	private Block crops;
	private String name;
	private Block soil;
	
	public ItemSeed(String name, BlockCrop crop, int tier, int color, Block soil) {
		super(crop, Blocks.FARMLAND);
		this.setUnlocalizedName(name + "_seeds");
		this.setRegistryName(name + "_seeds");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.soil = soil;
		this.crops = crop;
		this.tier = tier;
		this.color = color;
		this.name = name;
	}
		
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
        switch(this.tier - 1){
        case 0:
        	tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
            break;
        case 1:
        	tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
            break;
        case 2:
        	tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
            break;
        case 3:
        	tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
            break;
        case 4:
        	tooltip.add(Tooltips.TIER + Colors.RED + "5");
            break;
        case 5:
        	tooltip.add(Tooltips.TIER + Colors.DARK_PURPLE + "6");
        	break;
        }
    }
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String nameFinal = "";
		nameFinal += I18n.translateToLocal("tile.mysticalcreations.seeds.name.before");
		String[] name = this.name.split("_");
		if(name.length > 1) {
			for(String string : name) {
				nameFinal += string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
			}
		} else {
			nameFinal = name[0].substring(0, 1).toUpperCase() + name[0].substring(1) + " ";
		}
		nameFinal += I18n.translateToLocal("item.mysticalcreations.seeds.name");
		return nameFinal;
	}
	
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("mysticalcreations:base_seeds", "inventory"));
	}

}