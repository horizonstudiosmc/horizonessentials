package org.horizon.plugins.horizonessentials.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class HorizonGUI implements Listener {

    public Inventory inv;


    public void init(String name, int size) {
        inv = Bukkit.createInventory(null, size, name);
    }


    public void addItem(ItemStack item) {
        inv.addItem(item);
    };

    public void addItems(ItemStack[] items) {
        inv.addItem(items);
    }

    public void openInv(Player player) {
        player.openInventory(inv);
    }

    public abstract void inventoryEvent(InventoryClickEvent event, Player player);

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory() == inv) {
            inventoryEvent(event, player);
        }
    }


}
