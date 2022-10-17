package org.mcuni.kit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcuni.kit.Kit;

/**
 * Handles all /event based commands.
 */
public class WhitelistCommands implements CommandExecutor {

    public Kit plugin;

    /**
     * Constructor for the WhitelistCommands class.
     * @param plugin References to the main kit plugin class.
     */
    public WhitelistCommands(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] WhitelistCommands class loaded.");
    }

    /**
     * /whitelist command handler.
     * @param commandSender Information about who sent the command - player or console.
     * @param command Information about what command was sent.
     * @param s Command label - not used here.
     * @param args The command's arguments.
     * @return boolean true/false - was the command accepted and processed or not?
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if (player.hasPermission("kit.whitelist")) {
            if (command.getName().equalsIgnoreCase("whitelist")) {
                if (args.length > 0) {
                    if ("tempon".equals(args[0])) {
                        if (plugin.WhitelistOn) {
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[Kit] " + ChatColor.RED + "The whitelist is already on.");
                        } else {
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[Kit] " + ChatColor.GREEN + "The whitelist has been enabled. Now enforcing.");
                            plugin.WhitelistOn = true;
                            plugin.getConfig().set("WhitelistOn", true);
                            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                                p.kickPlayer("The whitelist has been turned on for this server, please rejoin if you're on the whitelist.");
                            }
                        }
                        return true;
                    } else if ("tempoff".equals(args[0])) {
                        if (plugin.WhitelistOn) {
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[Kit] " + ChatColor.GREEN + "The whitelist has been disabled.");
                            plugin.WhitelistOn = false;
                            plugin.getConfig().set("WhitelistOn", false);
                        } else {
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[Kit] " + ChatColor.RED + "The whitelist is already off.");
                        }
                    }
                } else {
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Whitelist ------------");
                    if (plugin.WhitelistOn) {
                        commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "The whitelist is on.");
                    } else {
                        commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "The whitelist is off.");
                    }
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/whitelist tempon - Temporarily turn the whitelist on");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/whitelist tempoff - Temporarily turn the whitelist off");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "The whitelist will revert to config settings after a restart or reload ("+plugin.getConfig().getBoolean("WhitelistOn")+")");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Whitelist ------------");
                    return true;
                }
            }
        } else {
            commandSender.sendMessage(ChatColor.DARK_RED + "[Kit] "+ChatColor.RED+"You don't have the required permissions to use this command.");
        }
        return true;
    }
}
