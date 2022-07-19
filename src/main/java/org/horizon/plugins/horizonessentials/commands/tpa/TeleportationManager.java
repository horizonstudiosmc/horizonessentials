package org.horizon.plugins.horizonessentials.commands.tpa;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizonessentials.HE;

import java.util.HashMap;
import java.util.Map;

public class TeleportationManager {

    public Map<Player, Player> playerslist = new HashMap<>();

    public TeleportationManager() {

    }

    public void request(Player sender, Player target) {
        if (playerslist.get(sender) != null) return;
        sender.sendMessage(HE.instance.getPrefix() + ChatColor.GREEN + "Sent request");
        target.sendMessage(HE.instance.getPrefix() + ChatColor.GREEN + sender.getName() + " has requested to teleport to you, you can disable auto GUI opening in /settings");
        playerslist.put(sender, target);
        RequestGUI gui = new RequestGUI(sender, target);
    }

}
