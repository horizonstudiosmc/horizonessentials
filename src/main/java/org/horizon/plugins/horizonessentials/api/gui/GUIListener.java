package org.horizon.plugins.horizonessentials.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIListener implements Listener {
    private final GUIManager guiManager = new GUIManager();

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        guiManager.removeGui(event.getInventory());
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        HorizonGUI gui = guiManager.getGui(event.getInventory());
        Bukkit.getLogger().info("testno");
        Bukkit.getLogger().info(gui.toString());
        Bukkit.getLogger().info(event.getInventory().toString());
        if(gui != null) {
            Bukkit.getLogger().info("test");
            gui.handleEvent(event);
        }
    }
}
