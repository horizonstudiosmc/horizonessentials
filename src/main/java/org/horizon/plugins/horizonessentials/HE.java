package org.horizon.plugins.horizonessentials;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.horizonessentials.api.combat.CombatManager;
import org.horizon.plugins.horizonessentials.api.gui.GUIListener;
import org.horizon.plugins.horizonessentials.api.gui.GUIManager;
import org.horizon.plugins.horizonessentials.commands.tp.NewTeleportCommand;
import org.horizon.plugins.horizonessentials.commands.tp.NewTeleportGUI;
import org.horizon.plugins.horizonessentials.commands.tpa.TPACommand;
import org.horizon.plugins.horizonessentials.commands.tpa.TeleportationManager;

public class HE extends JavaPlugin {

    public static HE instance;
    public static TeleportationManager teleportationManager;
    public static CombatManager combatManager;
    public static GUIManager manager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bstats.register(this);
        combatManager = new CombatManager();
        manager = new GUIManager();
        teleportationManager = new TeleportationManager();
        saveDefaultConfig();
        getCommand("tp").setExecutor(new NewTeleportCommand());
        getCommand("tpa").setExecutor(new TPACommand());
        getServer().getPluginManager().registerEvents(new CombatManager(), this);
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
