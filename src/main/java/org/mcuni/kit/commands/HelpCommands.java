package org.mcuni.kit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.mcuni.kit.Kit;

/**
 * Handles all /help based commands.
 */
public class HelpCommands implements CommandExecutor {

    public Kit plugin;

    /**
     * Constructor for the HelpCommands class.
     * @param plugin References to the main kit plugin class.
     */
    public HelpCommands(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] HelpCommands class loaded.");
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
        if (command.getName().equalsIgnoreCase("help")) {
            commandSender.sendMessage(ChatColor.GOLD + "------------- Help -------------");
            if (args.length > 0) {
                if ("contact".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.YELLOW + "Please contact "+plugin.getConfig().getString("ServerContactName")+" if you have any queries.");
                    commandSender.sendMessage(ChatColor.YELLOW + "Email: "+plugin.getConfig().getString("ServerContactEmail"));
                    return true;
                } else if ("technical".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.YELLOW + "Connection issues: https://mcuni.org/help/connection-issues");
                    commandSender.sendMessage(ChatColor.YELLOW + "Being disconnected: https://mcuni.org/help/disconnected");
                    commandSender.sendMessage(ChatColor.YELLOW + "Can't build: https://mcuni.org/help/cant-build");
                    commandSender.sendMessage(ChatColor.YELLOW + "Server status: https://mcuni.org/help/status");
                    return true;
                } else if ("plugins".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.YELLOW + "Land claiming: https://uopmc.com/help/land-claiming");
                    commandSender.sendMessage(ChatColor.YELLOW + "The shop: https://uopmc.com/help/the-shop");
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.YELLOW + "/help contact - Get in touch with a member of our support team.");
                commandSender.sendMessage(ChatColor.YELLOW + "/help technical - Get technical help.");
                commandSender.sendMessage(ChatColor.YELLOW + "/help plugins - Help with using server plugins and features.");
                return true;
            }
            commandSender.sendMessage(ChatColor.GOLD + "------------- Help -------------");
        }
        return true;
    }
}
