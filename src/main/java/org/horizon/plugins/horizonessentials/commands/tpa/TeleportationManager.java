package org.horizon.plugins.horizonessentials.commands.tpa;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.horizon.plugins.horizonessentials.HE;

import java.util.HashMap;
import java.util.Map;

public class TeleportationManager {

    public Map<Player, Player> playerslist = new HashMap<>();

    public TeleportationManager() {

    }

    public void request(Player sender, Player target) {
        if (playerslist.get(sender) != null) {
            target.sendMessage(ChatColor.RED + "There's already a request");
            return;
        }
        if (playerslist.get(target) == sender) {
            target.sendMessage(ChatColor.RED + "There's a request going the other way");
            return;
        }
        if (!(HE.combatManager.damaged.get(target) == null) || (target.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) || target.isInWater()) {
            sender.sendMessage(ChatColor.RED + "They are in a bad situation");
            return;
        }
        sender.sendMessage(HE.instance.getPrefix() + ChatColor.GREEN + "Sent request");
        target.sendMessage(HE.instance.getPrefix() + ChatColor.GREEN + sender.getName() + " has requested to teleport to you, this will not happen if you are in a bad situation.");
        playerslist.put(sender, target);
        RequestGUI gui = new RequestGUI(sender, target);

    }

}
