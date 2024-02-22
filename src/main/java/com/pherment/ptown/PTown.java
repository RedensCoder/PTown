package com.pherment.ptown;

import com.pherment.ptown.commands.PCommand;
import com.pherment.ptown.commands.PTabCompleter;
import com.pherment.ptown.configs.PConfig;
import com.pherment.ptown.events.RegionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PTown extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[PTown] Plugin Enabled!");

        PConfig.setup();
        PConfig.getTownConfig().options().copyDefaults(true);
        PConfig.loadAllTowns();

        getCommand("ptown").setExecutor(new PCommand());
        getCommand("ptown").setTabCompleter(new PTabCompleter());

        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[PTown] Plugin Disabled!");
    }
}
