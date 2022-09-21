package org.mcuni.kit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KitCommands implements CommandExecutor {
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