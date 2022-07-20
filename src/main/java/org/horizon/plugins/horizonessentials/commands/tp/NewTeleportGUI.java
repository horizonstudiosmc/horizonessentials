package org.horizon.plugins.horizonessentials.commands.tp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.horizon.plugins.horizonessentials.HE;
import org.horizon.plugins.horizonessentials.api.gui.GUIManager;
import org.horizon.plugins.horizonessentials.api.gui.HorizonGUI;
import org.horizon.plugins.horizonessentials.api.sound.EasySound;

import java.util.ArrayList;
import java.util.List;

public class NewTeleportGUI implements HorizonGUI {

    public Inventory inv;
    public NewTeleportGUI(Player player) {
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&dPlayer Portal"));
        for (Player p : Bukkit.getOnlinePlayers()) {
            if ((p == player)) {
                ItemStack plItem = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) plItem.getItemMeta();
                meta.setDisplayName(p.getName());
                List<String> lore = new ArrayList<>();
                if (player.hasPermission("horizonessentials.tp")) lore.add(ChatColor.GREEN + "Left click to teleport");
                if (!player.hasPermission("horizonessentials.tp")) lore.add(ChatColor.RED + "Left click to teleport (You do not have permission)");
                lore.add(ChatColor.GREEN + "Right click to send a teleportation request");
                meta.setLore(lore);
                meta.setOwningPlayer(p);
                plItem.setItemMeta(meta);
                inv.addItem(plItem);
            }
        }
        HE.manager.setGui(inv, this);
        player.openInventory(inv);
    }



    @Override
    public void handleEventLeftClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if (!player.hasPermission("horizonessentials.tp")) {
            EasySound.playSoundAtPlayer(player, Sound.ENTITY_VILLAGER_NO);
            return;
        }
        Player pl = Bukkit.getPlayer(inv.getItem(event.getSlot()).getItemMeta().getDisplayName());
        Bukkit.getLogger().info(inv.getItem(event.getSlot()).getItemMeta().getDisplayName());
        if (!(pl == null)) {
            player.closeInventory();
            player.teleport(pl);
            EasySound.playSoundAtPlayer(player, Sound.ITEM_ARMOR_EQUIP_DIAMOND);
            player.sendMessage(HE.instance.getPrefix() + ChatColor.GREEN + "You have been teleported");
        }
    }

    @Override
    public void handleEventRightClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        Player target = Bukkit.getPlayer(inv.getItem(event.getSlot()).getItemMeta().getDisplayName());
        if (target == null) {
            return;
        }
        HE.teleportationManager.request(player, target);
        player.closeInventory();
    }

    @Override
    public void handleClose(InventoryCloseEvent event) {
        HE.manager.removeGui(event.getInventory());
    }
}
