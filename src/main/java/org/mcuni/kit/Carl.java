package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;

public class Carl implements Listener {
    public void initialise() {
        getLogger().info("Init: Carl.");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent player) {
        if (player.getMessage().contains("pog")) {
            Bukkit.broadcastMessage(ChatColor.RED + "ew");
        }
    }

    public void shutdown() {
        getLogger().info("Disabled: Carl.");
    }
}
