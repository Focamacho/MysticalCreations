package com.focamacho.mysticalcreations.config;

import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.focamacho.mysticalcreations.MysticalCreations;
import com.focamacho.mysticalcreations.compat.CompatMysticalAgradditions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    public static List<Crop> getAllCrops() {
        if(!ConfigMysticalCreations.wrong) {
            List<Crop> allCrops = new ArrayList<Crop>();

            for(String string : ConfigMysticalCreations.allSeeds){
                Crop crop = getCrop(string);
                if(crop != null){
                    allCrops.add(crop);
                }
            }

            return allCrops;
        }

        return null;
    }

    private static Crop getCrop(String config){
        String[] split = config.split(";");
        try {
            if (split.length == 7) {
                ResourceLocation id = new ResourceLocation(MysticalCreations.MOD_ID, split[0]);
                CropTier tier = getTierFromNumber(Integer.parseInt(split[1]));
                CropType type = getTypeFromString(split[2]);
                CropTextures textures = getTexturesFromString(split[3].toUpperCase(), split[4].toUpperCase());
                int color = Integer.parseInt(split[5], 16);
                LazyIngredient craftingMaterial = LazyIngredient.EMPTY;

                if(tier == null){
                    MysticalCreations.LOGGER.error("Config file error. Invalid tier. Check the line \"" + config + "\"");
                    return null;
                }

                if(type == null){
                    MysticalCreations.LOGGER.error("Config file error. Invalid type. Check the line \"" + config + "\"");
                    return null;
                }

                if(textures == null){
                    MysticalCreations.LOGGER.error("Config file error. Invalid textures. Check the line \"" + config + "\"");
                    return null;
                }

                Crop crop = new Crop(id, tier, type, textures, color, craftingMaterial);
                return crop;

            } else {
                MysticalCreations.LOGGER.error("Invalid arguments length. Check the line \"" + config + "\"");
            }
        } catch(Exception e) {
            MysticalCreations.LOGGER.error("Config file error. Check the line \"" + config + "\"");
        }

        return null;
    }

    private static CropTier getTierFromNumber(int tier){
        switch(tier){
            case 1:
                return CropTier.ONE;
            case 2:
                return CropTier.TWO;
            case 3:
                return CropTier.THREE;
            case 4:
                return CropTier.FOUR;
            case 5:
                return CropTier.FIVE;
            case 6:
                return ModList.get().isLoaded("mysticalagradditions") ? CompatMysticalAgradditions.getTier6() : CropTier.FIVE;
        }
        return null;
    }

    private static CropType getTypeFromString(String string){
        switch(string){
            case "RESOURCE":
                return CropType.RESOURCE;
            case "MOB":
                return CropType.MOB;
        }
        return null;
    }

    private static CropTextures getTexturesFromString(String crop, String essence){
        ResourceLocation cropTexture = null;
        ResourceLocation essenceTexture = null;

        switch(crop){
            case "DUST":
                cropTexture = CropTextures.FLOWER_DUST_BLANK;
                break;
            case "INGOT":
                cropTexture = CropTextures.FLOWER_INGOT_BLANK;
                break;
            case "FACE":
                cropTexture = CropTextures.FLOWER_FACE_BLANK;
                break;
            case "ROCK":
                cropTexture = CropTextures.FLOWER_ROCK_BLANK;
                break;
        }

        switch(essence){
            case "DIAMOND":
                essenceTexture = CropTextures.ESSENCE_DIAMOND_BLANK;
                break;
            case "DUST":
                essenceTexture = CropTextures.ESSENCE_DUST_BLANK;
                break;
            case "FLAME":
                essenceTexture = CropTextures.ESSENCE_FLAME_BLANK;
                break;
            case "GEM":
                essenceTexture = CropTextures.ESSENCE_GEM_BLANK;
                break;
            case "INGOT":
                essenceTexture = CropTextures.ESSENCE_INGOT_BLANK;
                break;
            case "QUARTZ":
                essenceTexture = CropTextures.ESSENCE_QUARTZ_BLANK;
                break;
            case "ROCK":
                essenceTexture = CropTextures.ESSENCE_ROCK_BLANK;
                break;
            case "TALL_GEM":
                essenceTexture = CropTextures.ESSENCE_TALL_GEM_BLANK;
                break;
        }

        if(cropTexture == null) {
            MysticalCreations.LOGGER.error("Invalid Crop Texture name \"" + crop + "\"");
            return null;
        }
        if(essenceTexture == null) {
            MysticalCreations.LOGGER.error("Invalid Essence Texture name \"" + essence + "\"");
            return null;
        }

        return new CropTextures(cropTexture, essenceTexture);

    }

}
