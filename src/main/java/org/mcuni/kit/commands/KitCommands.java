package org.mcuni.kit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handles all /kit based commands.
 */
public class KitCommands implements CommandExecutor {
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
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/kit modules - Show enabled modules");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Kit Help ------------");
                    return true;
                } else if ("modules".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] Modules Enabled: Carl, Broadcast, Kit, Status");
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Running MCUni-Kit version 1.0-SNAPSHOT for Minecraft 1.19.2");
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Use /kit help for more commands");
                return true;
            }
        }
        return true;
    }
}
