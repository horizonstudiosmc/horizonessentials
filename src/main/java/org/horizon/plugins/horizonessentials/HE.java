package org.horizon.plugins.horizonessentials;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class HE extends JavaPlugin {

    public HE() {

    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
    }
}
