package org.mcuni.kit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mcuni.kit.Kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class Whitelist implements Listener {
    public Kit plugin;

    /**
     * Constructor for the Whitelist class.
     * @param plugin References to the main kit plugin class.
     */
    public Whitelist(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] Whitelist event handler started.");
    }

    /**
     * EventHandler for when the player joins the server. Runs the required functions to authenticate the user.
     * @param event The event data.
     */
    @EventHandler(priority= EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!Objects.requireNonNull(plugin.getConfig().getString("WhitelistRoles")).contains(getUserInfo(player.getName(), String.valueOf(player.getUniqueId())))) {
            getLogger().info("[Kit][Whitelist] Kicked player '"+player.getName()+"'. Player is not registered.");
            player.kickPlayer(ChatColor.DARK_RED +"You're not whitelisted. "+ChatColor.RED+"This server is currently closed for a special event. Please contact your server team for more information.");
        } else {
            getLogger().info("[Kit][Whitelist] Allowed player '"+player.getName()+"'. Player is registered.");
        }
    }

    /**
     * Fetches user information from the API.
     * @param username The player's username.
     * @param uuid The player's unique user ID
     * @return Integer related to the user's MCUni role number.
     */
    private String getUserInfo(String username, String uuid) {
        try {
            getLogger().info("[Kit][Whitelist] Fetching player data for user '"+username+"' with UUID '"+uuid+"'.");
            URL url = new URL("https://kit.mcuni.org/api/"+plugin.APIVersion+"/whitelist.php?username="+username+"&uuid="+uuid+"&network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey"));
            Scanner s = new Scanner(url.openStream());
            if (s.hasNextLine()) {
                String response = s.nextLine();
                if (response.equals("")) {
                    getLogger().info("[Kit][Whitelist] There was no response from the server.");
                    return "0";
                } else {
                    getLogger().info("[Kit][Whitelist] Fetched: " + response);
                    return response;
                }
            }
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Whitelist] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
        getLogger().info("[DEBUG] https://kit.mcuni.org/api/"+plugin.APIVersion+"/whitelist.php?username="+username+"&uuid="+uuid+"&network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey"));
        return "0";
    }
}