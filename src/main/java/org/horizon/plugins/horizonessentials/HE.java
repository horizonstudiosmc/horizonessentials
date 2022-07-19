package org.horizon.plugins.horizonessentials;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.horizonessentials.commands.tp.NewTeleportCommand;

public class HE extends JavaPlugin {

    public HE() {

    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getCommand("tp").setExecutor(new NewTeleportCommand());
        getServer().getPluginManager().registerEvents(new NewTeleportCommand(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
    }
}
