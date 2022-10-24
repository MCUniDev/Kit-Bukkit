package org.mcuni.kit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.mcuni.kit.Kit;

/**
 * Handles all /map based commands.
 */
public class MapCommands implements CommandExecutor {

    public Kit plugin;

    /**
     * Constructor for the MapCommands class.
     * @param plugin References to the main kit plugin class.
     */
    public MapCommands(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] MapCommands class loaded.");
    }

    /**
     * /help command handler.
     * @param commandSender Information about who sent the command - player or console.
     * @param command Information about what command was sent.
     * @param s Command label - not used here.
     * @param args The command's arguments.
     * @return boolean true/false - was the command accepted and processed or not?
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("map")) {
            commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "Please visit https://map.mcuni.org/"+plugin.getConfig().getString("NetworkID").toLowerCase()+" to see the online maps." +
                    "://.");
            return true;
        }
        return true;
    }
}
