package org.horizon.plugins.horizonessentials.commands.tp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.horizon.plugins.horizonessentials.api.gui.HorizonGUI;
import org.horizon.plugins.horizonessentials.api.sound.EasySound;

public class NewTeleportGUI implements HorizonGUI {

    public Inventory inv;
    public NewTeleportGUI(Player player) {
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&dPlayer Portal"));
        for (Player p : Bukkit.getOnlinePlayers()) {
            if ((p == player)) {
                ItemStack plItem = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) plItem.getItemMeta();
                meta.setDisplayName(p.getName());
                meta.setOwningPlayer(p);
                plItem.setItemMeta(meta);
                inv.addItem(plItem);
            }
        }
        player.openInventory(inv);
    }



    @Override
    public void handleEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("test");
        Bukkit.getLogger().info(event.getInventory().toString());
        event.setCancelled(true);
        Player pl = Bukkit.getPlayer(inv.getItem(event.getSlot()).getItemMeta().getDisplayName());
        Bukkit.getLogger().info(inv.getItem(event.getSlot()).getItemMeta().getDisplayName());
        if (!(pl == null)) {
            player.closeInventory();
            player.teleport(pl);
            EasySound.playSoundAtPlayer(player, Sound.ITEM_ARMOR_EQUIP_DIAMOND);
            player.sendMessage();
        }
    }
}
