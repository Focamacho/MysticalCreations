package com.focamacho.mysticalcreations.lib;

import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.focamacho.mysticalcreations.config.ConfigHandler;

import java.util.List;

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
}
