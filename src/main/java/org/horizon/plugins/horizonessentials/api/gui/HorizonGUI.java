package org.horizon.plugins.horizonessentials.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.horizon.plugins.horizonessentials.api.sound.EasySound;

public abstract class HorizonGUI {

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

    public abstract void invClick(InventoryClickEvent event, Player player);




}
