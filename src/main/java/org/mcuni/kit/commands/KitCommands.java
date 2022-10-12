package org.mcuni.kit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.mcuni.kit.Kit;

/**
 * Handles all /kit based commands.
 */
public class KitCommands implements CommandExecutor {

    public Kit plugin;

    /**
     * Constructor for the KitCommands class.
     * @param plugin References to the main kit plugin class.
     */
    public KitCommands(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] KitCommands class loaded.");
    }

    /**
     * /kit command handler.
     * @param commandSender Information about who sent the command - player or console.
     * @param command Information about what command was sent.
     * @param s Command label - not used here.
     * @param args The command's arguments.
     * @return boolean true/false - was the command accepted and processed or not?
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("kit")) {
            if (args.length > 0) {
                if ("help".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Kit Help ------------");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/kit - Main command");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/kit help - Show enabled commands");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/kit enabled - Show enabled features");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/help - Help system");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event - Events system");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/whitelist - Whitelist system");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Kit Help ------------");
                    return true;
                } else if ("enabled".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] Modules Enabled: Broadcast, ItemManager, Kit, Status");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] Event Handlers Enabled: Carl, StatusStart, Whitelist");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] Command Handlers Enabled: EventsCommands, HelpCommands, KitCommands, WhitelistCommands");
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Running MCUni-Kit "+plugin.getDescription().getVersion()+" for Minecraft "+plugin.getDescription().getAPIVersion());
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Use /kit help for more commands");
                return true;
            }
        }
        return true;
    }
}
