package org.mcuni.kit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mcuni.kit.Kit;

public class Carl implements Listener {
    public Kit plugin;

    public Carl(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit][Carl] Carl event handler started.");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent player) {
        if (player.getMessage().contains("pog")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > " + ChatColor.RED + "ew");
        } else if (player.getMessage().contains("kek")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > " + ChatColor.RED + "ew");
        }
    }
}
