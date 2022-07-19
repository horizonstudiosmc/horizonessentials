package org.horizon.plugins.horizonessentials;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.horizonessentials.api.gui.GUIListener;
import org.horizon.plugins.horizonessentials.commands.tp.NewTeleportCommand;
import org.horizon.plugins.horizonessentials.commands.tp.NewTeleportGUI;

public class HE extends JavaPlugin {

    public static HE instance;



    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        getCommand("tp").setExecutor(new NewTeleportCommand());
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix"));
    }
}
