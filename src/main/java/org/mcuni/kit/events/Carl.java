package org.mcuni.kit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mcuni.kit.Kit;

/**
 * This class handles interception and parsing of player messages to respond "ew" if they send "pog" or "kek" in chat.
 */
public class Carl implements Listener {
    public Kit plugin;

    /**
     * Constructor for the Carl event class.
     * @param plugin References to the main kit plugin class.
     */
    public Carl(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] Carl event handler started.");
    }

    /**
     * Carl's chat handler. When the AsyncPlayerChatEvent event is thrown by Bukkit, Carl will catch it here and execute
     * its own code to decide if it should output "ew" or not.
     * @param player Fetches the instance of player that has thrown the event.
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent player) {
        String message = player.getMessage().toLowerCase();
        if (message.contains("pog")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > " + ChatColor.RED + "ew");
        } else if (message.contains("p0g")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > " + ChatColor.RED + "ew");
        } else if (message.contains("kek")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > " + ChatColor.RED + "ew");
        } else if (message.contains("hello")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > Hey!");
        } else if (message.contains("hi")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > Hey!");
        } else if (message.contains("hey")) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > Hey!");
        }
    }
}
