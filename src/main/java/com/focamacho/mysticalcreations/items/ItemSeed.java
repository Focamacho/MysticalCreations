package com.focamacho.mysticalcreations.items;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.focamacho.mysticalcreations.blocks.BlockCrop;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSeed extends ItemSeeds {

	private final int tier;
	private final String name;
	
	public ItemSeed(String name, BlockCrop crop, int tier) {
		super(crop, Blocks.FARMLAND);
		this.setUnlocalizedName(name + "_seeds");
		this.setRegistryName(name + "_seeds");
		this.setCreativeTab(MysticalAgriculture.CREATIVE_TAB);
		this.tier = tier;
		this.name = name;
	}
		
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
        switch(this.tier){
        case 1:
        	tooltip.add(Tooltips.TIER + Colors.YELLOW + "1");
            break;
        case 2:
        	tooltip.add(Tooltips.TIER + Colors.GREEN + "2");
            break;
        case 3:
        	tooltip.add(Tooltips.TIER + Colors.GOLD + "3");
            break;
        case 4:
        	tooltip.add(Tooltips.TIER + Colors.AQUA + "4");
            break;
        case 5:
        	tooltip.add(Tooltips.TIER + Colors.RED + "5");
            break;
        case 6:
        	tooltip.add(Tooltips.TIER + Colors.DARK_PURPLE + "6");
        	break;
        }
    }
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		StringBuilder nameFinal = new StringBuilder();
		nameFinal.append(I18n.translateToLocal("item.mysticalcreations.seeds.name.before"));
		String[] name = this.name.split("_");
		if(name.length > 1) {
			for(String string : name) {
				nameFinal.append(string.substring(0, 1).toUpperCase()).append(string.substring(1)).append(" ");
			}
		} else {
			nameFinal.append(name[0].substring(0, 1).toUpperCase()).append(name[0].substring(1)).append(" ");
		}
		nameFinal.append(I18n.translateToLocal("item.mysticalcreations.seeds.name"));
		return nameFinal.toString();
	}

}