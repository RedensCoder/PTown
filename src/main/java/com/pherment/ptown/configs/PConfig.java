package com.pherment.ptown.configs;

import com.pherment.ptown.utils.Town;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PConfig {
    private static File file;
    private static FileConfiguration townConfig;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("PTown").getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        townConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static void loadAllTowns() {
        List<Town> towns = new ArrayList<>();
        ConfigurationSection townSection = townConfig.getConfigurationSection("towns");
        if (townSection != null) {
            for (String key : townSection.getKeys(false)) {
                ConfigurationSection townData = townSection.getConfigurationSection(key);
                String name = townData.getString("name");
                Location pos1 = Location.deserialize(townData.getConfigurationSection("pos1").getValues(true));
                Location pos2 = Location.deserialize(townData.getConfigurationSection("pos2").getValues(true));
                towns.add(new Town(name, pos1, pos2));
            }
        }

        for (Town t : towns) {
            System.out.println(t);
            Town.townList.add(t);
        }
    }

    public static void save() {
        ConfigurationSection townSection = townConfig.createSection("towns");
        for (int i = 0; i < Town.townList.size(); i++) {
            Town town = Town.townList.get(i);
            ConfigurationSection townData = townSection.createSection("town" + i);
            townData.set("name", town.getName());
            townData.set("pos1", town.getPos1().serialize());
            townData.set("pos2", town.getPos2().serialize());
        }
        try {
            townConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        townConfig = YamlConfiguration.loadConfiguration(file);
    }

    /* ===== GETTERS & SETTERS ===== */

    public static FileConfiguration getTownConfig() {
        return townConfig;
    }
}
