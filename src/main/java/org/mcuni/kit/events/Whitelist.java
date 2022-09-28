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
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class Whitelist implements Listener {
    public Kit plugin;

    /**
     * Constructor for the Whitelist event class.
     * @param plugin References to the main kit plugin class.
     * @deprecated Does not work if MCUni Verify is installed on the proxy because it overrides Kit's functionality here.
     */
    @Deprecated
    public Whitelist(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] Whitelist event handler started.");
    }

    /**
     * Listener for if players join the server.
     * @param event Catches the details of the event.
     * @deprecated Does not work if MCUni Verify is installed on the proxy because it overrides Kit's functionality here.
     */
    @Deprecated
    @EventHandler(priority= EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!getUserInfo(player.getName(), String.valueOf(player.getUniqueId()))) {
            getLogger().info("[Kit][Whitelist] Kicked player '"+player.getName()+"'. Player is not registered.");
            player.kickPlayer(ChatColor.GOLD+""+ChatColor.BOLD+"Welcome to "+plugin.ServerNickname+".\n"+
                    "\n"+
                    ChatColor.RESET+""+ChatColor.YELLOW+"This server is only for students at "+plugin.UniversityName+".\n"+
                    "You need to verify that you're a student before you can join.\n"+
                    "\n"+
                    "To verify your account please visit mcuni.org/verify");
        } else {
            getLogger().info("[Kit][Whitelist] Allowed player '"+player.getName()+"'. Player is registered.");
        }
    }

    /**
     * API calls to fetch a user's information from the MCUni Kit server.
     * @param username The player's username.
     * @param uuid The player's unique user identification number.
     * @return boolean True/false - should the user be allowed on the server or not.
     * @deprecated Does not work if MCUni Verify is installed on the proxy because it overrides Kit's functionality here.
     */
    @Deprecated
    private boolean getUserInfo(String username, String uuid) {
        try {
            getLogger().info("[Kit][Whitelist] Fetching player data for user '"+username+"' with UUID '"+uuid+"'.");
            URL url = new URL("https://kit.mcuni.org/api/v1/user.php?username="+username+"&uuid="+uuid+"&network="+plugin.NetworkID);
            getLogger().info("[DEBUG] https://kit.mcuni.org/api/v1/user.php?username="+username+"&uuid="+uuid+"&network="+plugin.NetworkID);
            Scanner s = new Scanner(url.openStream());
            if (s.hasNextLine()) {
                String response = s.nextLine();
                if (response.equals("")) {
                    getLogger().info("[Kit][Whitelist] There was no response from the server.");
                    return false;
                } else {
                    getLogger().info("[Kit][Whitelist] Fetched: " + response);
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