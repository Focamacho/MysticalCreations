package com.focamacho.mysticalcreations.items;

import java.util.List;

import javax.annotation.Nullable;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.util.IHasModel;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChunk extends Item implements IHasModel {
	
	private static int color;
	private int tier;
	private String name;
	
	public ItemChunk(String name, int tier, int color) {
		this.setRegistryName(name + "_chunk");
		this.setUnlocalizedName(name + "_chunk");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.color = color;
		this.name = name;
		this.tier = tier;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String nameFinal = "";
		nameFinal += I18n.translateToLocal("tile.mysticalcreations.chunk.name.before");
		String[] name = this.name.split("_");
		if(name.length > 1) {
			for(String string : name) {
				nameFinal += string.substring(0, 1).toUpperCase() + string.substring(1) + " ";
			}
		} else {
			nameFinal = name[0].substring(0, 1).toUpperCase() + name[0].substring(1) + " ";
		}
		nameFinal += I18n.translateToLocal("item.mysticalcreations.chunk.name");
		return nameFinal;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
        switch(this.tier - 1){
        case 0:
        	tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.YELLOW + "30%");
            break;
        case 1:
        	tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
    		tooltip.add(Tooltips.DROP_CHANCE + Colors.GREEN + "25%");
            break;
        case 2:
        	tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.GOLD + "20%");
            break;
        case 3:
        	tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.AQUA + "15%");
            break;
        case 4:
        	tooltip.add(Tooltips.TIER + Colors.RED + "5");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.RED + "10%");
            break;
        case 5:
        	tooltip.add(Tooltips.TIER + Colors.DARK_PURPLE + "6");
        	tooltip.add(Tooltips.DROP_CHANCE + Colors.DARK_PURPLE + "5%");
        }
    }
	
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("mysticalcreations:base_chunk", "inventory"));
	}

}