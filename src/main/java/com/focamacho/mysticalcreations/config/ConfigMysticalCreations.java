package com.focamacho.mysticalcreations.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMysticalCreations {

    private static TomlWriter writer = new TomlWriter.Builder()
                                            .indentValuesBy(2)
                                            .indentTablesBy(4)
                                            .build();
    private static Toml toml;
    private static SeedConfig seeds = new SeedConfig();
    public static List<String> allSeeds;
    public static boolean wrong = false;


    public static void initConfigs(){
        try {
            //Get the config file from configs folder
            File configFile = new File(Minecraft.getInstance().gameDir.getAbsolutePath() + "/config", "mysticalcreations-seeds.toml");

            //Create the config file and write comments
            if(!configFile.exists()) {
                configFile.createNewFile();
            }

            //Read the config file to TOML
            toml = new Toml().read(configFile);

            //Check if the config file contains all keys
            if(toml.getList("mysticalcreations.seeds") == null) {
                seeds.mysticalcreations.put("_comment", new String[]{"Check the wiki to learn how to create your seeds! https://github.com/Focamacho/MysticalCreations/wiki"});
                seeds.mysticalcreations.put("seeds", new String[]{"cake;3;RESOURCE;DUST;DIAMOND;D303FC", "witch;4;MOB;FACE;FLAME;FF0324"});
                writer.write(seeds, configFile);
                toml = new Toml().read(configFile);
            }

            allSeeds = toml.getList("mysticalcreations.seeds");

        } catch(Exception e) {
            wrong = true;
            e.printStackTrace();
        }
    }

    static class SeedConfig{
        Map<String, String[]> mysticalcreations = new HashMap<String, String[]>();
    }

}
