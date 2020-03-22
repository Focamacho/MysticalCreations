package com.focamacho.mysticalcreations.lib;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.config.ConfigHandler;
import com.focamacho.mysticalcreations.config.ConfigMysticalCreations;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

@MysticalAgriculturePlugin
public class ModCorePlugin implements IMysticalAgriculturePlugin {

    public static List<Crop> allCrops;

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        allCrops = ConfigHandler.getAllCrops();
        for(Crop crop : allCrops){
            registry.register(crop);
        }
    }

    public static void setCruxToCrops(){
        for(String config : ConfigMysticalCreations.allSeeds) {
            String[] split = config.split(";");
            if(!split[6].equals("null") && ForgeRegistries.BLOCKS.getValue(new ResourceLocation(split[6])) != null) {
                Supplier<Block> crux = () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(split[6]));
                MysticalAgricultureAPI.getCropRegistry().getCropById(new ResourceLocation(MysticalCreations.MOD_ID, split[0])).setCrux(crux);
            }
        }
    }

}
