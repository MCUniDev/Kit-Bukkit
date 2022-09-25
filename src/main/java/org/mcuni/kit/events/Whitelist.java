package org.mcuni.kit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerLoginEvent;
import org.mcuni.kit.Kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class Whitelist implements Listener {
    public Kit plugin;

    public Whitelist(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit][Carl] Carl event handler started.");
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onJoin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if (!getUserInfo(player.getName(), String.valueOf(player.getUniqueId()))) {
            player.kickPlayer("Welcome to UOPMC.\n"+
                    "You'll need to verify that you're a student before you can play.\n"+
                    "\n"+
                    "For more information please visit mcuni.org/verify");
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Welcome back "+player.getDisplayName()+".");
        }
    }

    private boolean getUserInfo(String username, String uuid) {
        try {
            getLogger().info("[Kit][Broadcast] Fetching player data for user '"+username+"' with UUID '"+uuid+"'.");
            URL url = new URL("https://kit.mcuni.org/api/v1/user.php?username="+username+"&uuid="+uuid);
            getLogger().info("[DEBUG] https://kit.mcuni.org/api/v1/user.php?username="+username+"&uuid="+uuid);
            Scanner s = new Scanner(url.openStream());
            if (s.hasNextLine()) {
                String response = s.nextLine();
                if (response.equals("")) {
                    getLogger().info("[Kit][Broadcast] There was no response from the server.");
                    return false;
                } else {
                    getLogger().info("[Kit][Broadcast] Fetched: " + response);
                    return response.equals("true");
                }
            }
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Broadcast] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
        return false;
    }
}