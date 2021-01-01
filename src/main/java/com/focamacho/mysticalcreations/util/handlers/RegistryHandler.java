package com.focamacho.mysticalcreations.util.handlers;

import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.items.ItemChunk;
import com.focamacho.mysticalcreations.items.ItemCrop;
import com.focamacho.mysticalcreations.items.ItemEssence;
import com.focamacho.mysticalcreations.items.ItemSeed;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        MysticalCreations.items.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        MysticalCreations.blocks.forEach(event.getRegistry()::register);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        MysticalCreations.items.forEach(item -> {
            if(item instanceof ItemCrop) MysticalCreations.proxy.setItemResourceLocation(item, "mysticalcreations:base_crop");
            else if(item instanceof ItemEssence) MysticalCreations.proxy.setItemResourceLocation(item, "mysticalcreations:base_essence");
            else if(item instanceof ItemSeed) MysticalCreations.proxy.setItemResourceLocation(item, "mysticalcreations:base_seeds");
            else if(item instanceof ItemChunk) MysticalCreations.proxy.setItemResourceLocation(item, "mysticalcreations:base_chunk");
        });

        MysticalCreations.blocks.forEach(MysticalCreations.proxy::setCropResourceLocation);
    }

}
