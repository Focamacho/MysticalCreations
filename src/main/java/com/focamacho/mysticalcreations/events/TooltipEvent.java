package com.focamacho.mysticalcreations.events;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.lib.ModCorePlugin;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TooltipEvent {

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event){
        for(Crop crop : ModCorePlugin.allCrops){
            if(crop.getModId().contentEquals(MysticalCreations.MOD_ID)){
                Item item = event.getItemStack().getItem();
                if(crop.getEssence().equals(item) || crop.getSeeds().equals(item)) {
                    event.getItemStack().setDisplayName(new StringTextComponent(TextFormatting.RESET + event.getItemStack().getDisplayName().getString().replace("crop.mysticalcreations." + crop.getName(),  crop.getName().substring(0, 1).toUpperCase() + crop.getName().substring(1))));
                    break;
                }
            }
        }
    }
}
