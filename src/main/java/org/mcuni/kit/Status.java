package org.mcuni.kit;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

/**
 * This class handles sending status messages to MCUni's network operations center.
 */
public class Status {

    public Kit plugin;

    /**
     * Constructor for the Status class.
     * @param plugin References to the main kit plugin class.
     */
    public Status(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] Status module started.");
    }

    public void sendStatus(String Status) {
        try {
            URL url = new URL("https://kit.mcuni.org/api/v2/status.php?network="+plugin.NetworkID+"&server="+plugin.ServerID+"&status="+Status);
            new Scanner(url.openStream());
            getLogger().info("[MCUni-Kit][Status] Sent Status Ping to MCUni Network.");
            getLogger().info("[MCUni-Kit][Status][DEBUG] https://kit.mcuni.org/api/v2/status.php?network="+plugin.NetworkID+"&server="+plugin.ServerID+"&status="+Status);
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Status] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
    }
}
