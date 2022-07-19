package org.horizon.plugins.horizonessentials.commands.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.horizon.plugins.horizonessentials.HE;
import org.horizon.plugins.horizonessentials.api.gui.HorizonGUI;

public class RequestGUI implements HorizonGUI {

    public Inventory inv;
    Player player;
    Player target;

    public RequestGUI(Player player, Player target) {
        this.player = player;
        this.target = target;
        inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&dPlayer Portal"));
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack yes = new ItemStack(Material.GREEN_STAINED_GLASS);
        ItemMeta metayes = yes.getItemMeta();
        metayes.setDisplayName(ChatColor.GREEN + "ACCEPT");
        yes.setItemMeta(metayes);
        ItemStack no = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta metano = no.getItemMeta();
        metano.setDisplayName(ChatColor.RED + "DENY");
        no.setItemMeta(metano);
        inv.setItem(0, yes);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, no);
        HE.manager.setGui(inv, this);
        target.openInventory(inv);
    }

    @Override
    public void handleEventLeftClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Bukkit.getLogger().info(String.valueOf(event.getSlot()));
        if (event.getSlot() == 0) {
            player.teleport(target);
            player.sendMessage(ChatColor.GREEN + "Accepted");
            target.sendMessage(ChatColor.GREEN + "Accepted");
            target.closeInventory();
            return;
        }
        if (event.getSlot() == 8) {
            player.sendMessage(ChatColor.RED + "Denied");
            target.sendMessage(ChatColor.RED + "Denied");
            HE.teleportationManager.playerslist.remove(player, target);
            HE.manager.removeGui(event.getInventory());
            target.closeInventory();
            return;
        }
    }

    @Override
    public void handleEventRightClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void handleClose(InventoryCloseEvent event) {
        HE.teleportationManager.playerslist.remove(player);
        HE.manager.removeGui(event.getInventory());
    }
}
