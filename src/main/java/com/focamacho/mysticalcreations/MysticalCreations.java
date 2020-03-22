package com.focamacho.mysticalcreations;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.focamacho.mysticalcreations.config.ConfigMysticalCreations;
import com.focamacho.mysticalcreations.events.TooltipEvent;
import com.focamacho.mysticalcreations.lib.ModCorePlugin;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalcreations")
public class MysticalCreations
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static String MOD_ID = "mysticalcreations";

    public MysticalCreations() {
        ConfigMysticalCreations.initConfigs();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModCorePlugin.setCruxToCrops();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new TooltipEvent());
    }

}
