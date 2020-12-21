package com.focamacho.mysticalcreations.items;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemChunk extends Item {

	private final int tier;
	private final String name;
	
	public ItemChunk(String name, int tier) {
		this.setRegistryName(name + "_chunk");
		this.setUnlocalizedName(name + "_chunk");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.name = name;
		this.tier = tier;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String nameFinal = "";
		nameFinal += I18n.translateToLocal("item.mysticalcreations.chunk.name.before");
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

}
