package org.horizon.plugins.horizonessentials.commands.tp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.horizon.plugins.horizonessentials.HE;
import org.horizon.plugins.horizonessentials.api.gui.HorizonGUI;
import org.horizon.plugins.horizonessentials.api.sound.EasySound;

public class NewTeleportGUI extends HorizonGUI implements Listener {

    public NewTeleportGUI(Player player) {


        init(ChatColor.translateAlternateColorCodes('&', "&dPlayer Portal"), 9);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!(p == player)) {
                ItemStack plItem = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) plItem.getItemMeta();
                meta.setDisplayName(p.getName());
                meta.setOwningPlayer(p);
                plItem.setItemMeta(meta);
                addItem(plItem);
            }
        }
        openInv(player);
    }


    @Override
    public void invClick(InventoryClickEvent event, Player player) {
        event.setCancelled(true);
        Player pl = Bukkit.getPlayer(inv.getItem(event.getSlot()).getItemMeta().getDisplayName());
        if (!(pl == null)) {
            player.closeInventory();
            player.teleport(pl);
            EasySound.playSoundAtPlayer(player, Sound.ITEM_GOAT_HORN_SOUND_6);
            player.sendMessage();
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory() == inv) {
            invClick(event, player);
        }
    }
}
