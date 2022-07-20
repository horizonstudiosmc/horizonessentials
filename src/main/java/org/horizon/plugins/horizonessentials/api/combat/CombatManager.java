package org.horizon.plugins.horizonessentials.api.combat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.horizon.plugins.horizonessentials.HE;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombatManager implements Listener {

    public Map<Player, Integer> damaged = new HashMap<>();
    private int taskid;


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            damaged.remove(player);
            damaged.put(player, 31);
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.cancelTask(taskid);
            taskid = scheduler.scheduleSyncRepeatingTask(HE.instance, () -> {
                if(damaged.get(player) <= 1){
                    damaged.remove(player);
                    scheduler.cancelTask(taskid);
                    TextComponent done = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7(&a0&7) &aCombat Tag Ended"));
                    done.setColor(ChatColor.GREEN);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, done);
                    return;
                }
                damaged.put(player, damaged.get(player) - 1);
                TextComponent message = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7(&f" + damaged.get(player) + "&7) &cCombat Tagged"));
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
            }, 0, 20);
        }
    }
}
