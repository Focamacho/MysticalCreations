package com.focamacho.mysticalcreations.proxy;

import com.focamacho.mysticalcreations.seeds.CustomSeed;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class CommonProxy {

    //Client Methods
    public void registerItemColorHandler(Item item, int color, int tintIndex) {}
    public void registerBlockColorHandler(CustomSeed seed) {}
    public void setCropResourceLocation(Block block) {}
    public void setItemResourceLocation(Item item, String location) {}

}
