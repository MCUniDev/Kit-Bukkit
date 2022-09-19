package org.mcuni.kit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("kit")) {
            if (args.length > 0) {
                if (args[0].equals("modules")) {
                    commandSender.sendMessage(ChatColor.GOLD + "Bounty, Broadcast, Config, Kit, Status");
                }
            } else {
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Running Kit version 1.0-SNAPSHOT for Minecraft 1.19.2");
                commandSender.sendMessage(ChatColor.GOLD + "[Kit] Commands: /kit, /kit modules");
            }
        }
        return true;
    }
}
