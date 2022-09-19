package org.mcuni.kit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Events implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("event")) {
            if (args.length > 0) {
                if ("help".equals(args[0])) {
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Events Help ------------");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event - Main command");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event help - Show enabled commands");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event starting - Send event starting soon broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event start - Send event start broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event hungergames - Send event start broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event murdermystery - Send event start broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event buildbattle - Send event start broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event thebridge - Send event start broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] " + ChatColor.YELLOW + "/event villagedefense - Send event start broadcast");
                    commandSender.sendMessage(ChatColor.GOLD + "[Kit] ------------ Events Help ------------");
                    return true;
                } else if ("starting".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The event is starting soon. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                } else if ("start".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                } else if ("hungergames".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'Hunger Games' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                } else if ("murdermystery".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'Murder Mystery' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                } else if ("buildbattle".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'BuildBattle' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                } else if ("thebridge".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'The Bridge' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                } else if ("villagedefense".equals(args[0])) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'Village Defense' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                    return true;
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Event scheduler enabled.");
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Use /event help for more commands.");
                return true;
            }
        }
        return true;
    }
}
