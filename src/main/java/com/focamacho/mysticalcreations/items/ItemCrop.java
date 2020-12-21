package com.focamacho.mysticalcreations.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemCrop extends ItemBlock {

    private final String name;

    public ItemCrop(Block block, String name) {
        super(block);
        this.setCreativeTab((CreativeTabs)null);
        this.setRegistryName(name + "_crop");
        this.name = name;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        StringBuilder nameFinal = new StringBuilder();
        nameFinal.append(I18n.translateToLocal("tile.mysticalcreations.crop.name.before"));
        String[] name = this.name.split("_");
        if(name.length > 1) {
            for(String string : name) {
                nameFinal.append(string.substring(0, 1).toUpperCase()).append(string.substring(1)).append(" ");
            }
        } else {
            nameFinal.append(name[0].substring(0, 1).toUpperCase()).append(name[0].substring(1)).append(" ");
        }
        nameFinal.append(I18n.translateToLocal("tile.mysticalcreations.crop.name"));
        return nameFinal.toString();
    }

}