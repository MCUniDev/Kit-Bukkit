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
                switch (args[0]) {
                    case "help":
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
                        break;
                    case "starting":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The event is starting soon. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                    case "start":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                    case "hungergames":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'Hunger Games' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                    case "murdermystery":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'Murder Mystery' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                    case "buildbattle":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'BuildBattle' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                    case "thebridge":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'The Bridge' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                    case "villagedefense":
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Event > " + ChatColor.YELLOW + "The 'Village Defense' event is now starting. Please join the Discord voice call. https://discord.gg/SCNRa3cQz9");
                        break;
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Event scheduler enabled.");
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Use /event help for more commands.");
            }
        }
        return true;
    }
}
