package org.horizon.plugins.horizonessentials.commands.tp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.horizon.plugins.horizonessentials.HE;
import org.horizon.plugins.horizonessentials.api.gui.GUIManager;
import org.horizon.plugins.horizonessentials.api.sound.EasySound;

public class NewTeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            HE he = HE.instance;

            if (args.length == 1) {
                if (!player.hasPermission("horizonessentials.tp")) {
                    player.sendMessage(he.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission!"));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(he.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&cThat player is not online"));
                }
                EasySound.playSoundAtPlayer(player, Sound.ITEM_GOAT_HORN_SOUND_6);
                player.teleport(target);
                player.sendMessage(he.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&aTeleported to &f" + target.getName()));
                return true;

            }

            player.sendMessage(he.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&fopened teleportation GUI, if you don't want to do this, use a argument"));
            NewTeleportGUI teleportGUI = new NewTeleportGUI(player);
            GUIManager manager = new GUIManager();
            manager.setGui(teleportGUI.inv, teleportGUI);
        }
        return false;
    }


}
