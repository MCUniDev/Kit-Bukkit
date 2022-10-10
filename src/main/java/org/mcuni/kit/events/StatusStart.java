package org.mcuni.kit.events;

import org.bukkit.event.server.ServerLoadEvent;
import org.mcuni.kit.Status;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.mcuni.kit.Kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

/**
 * This class handles sending the final "started" status message..
 */
public class StatusStart implements Listener {
    public Kit plugin;
    public Status statusClass;

    /**
     * Constructor for the StatusStart event class.
     * @param plugin References to the main kit plugin class.
     */
    public StatusStart(Kit plugin) {
        this.plugin = plugin;
        statusClass = new Status(plugin);
        Bukkit.getLogger().info("[MCUni-Kit] StatusStart event handler started.");
    }

    /**
     * Detects when the server has completed startup.
     * @param type Type of reload/restart.
     */
    @EventHandler
    public void onPlayerChat(ServerLoadEvent type) {
        int Status = 2;
        try {
            URL url = new URL("https://kit.mcuni.org/api/"+plugin.APIVersion+"/status.php?network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey")+"&status="+Status);
            new Scanner(url.openStream());
            getLogger().info("[MCUni-Kit][Status] Sent Status Ping to MCUni Network.");
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Status] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
        getLogger().info("[MCUni-Kit][Status][DEBUG] https://kit.mcuni.org/api/"+plugin.APIVersion+"/status.php?network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey")+"&status="+Status);
    }
}
