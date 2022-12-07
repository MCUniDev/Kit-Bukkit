package org.mcuni.kit;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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

    public void statusTimer() {
        Bukkit.getLogger().info("[MCUni-Kit][Status] Broadcast timer started.");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.mcuni.org/"+plugin.APIVersion+"/status/?network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey")+"&status=2");
                    new Scanner(url.openStream());
                    getLogger().info("[MCUni-Kit][Status] Sent Status Ping to MCUni Network.");
                } catch (IOException ex) {
                    getLogger().severe("[MCUni-Kit][Status] Fatal error.");
                    getLogger().severe(Arrays.toString(ex.getStackTrace()));
                }
            }
        }, 30*60*1000, 5*60*1000);
    }

    public void sendStatus(int Status) {
        try {
            URL url = new URL("https://kit.mcuni.org/api/"+plugin.APIVersion+"/status.php?network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey")+"&status="+Status);
            new Scanner(url.openStream());
            getLogger().info("[MCUni-Kit][Status] Sent Status Ping to MCUni Network.");
        }
        catch(IOException ex) {
            getLogger().severe("[MCUni-Kit][Status] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
        getLogger().info("[MCUni-Kit][Status][DEBUG] https://kit.mcuni.org/api/"+plugin.APIVersion+"/status.php?network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&key="+plugin.getConfig().getString("APIKey")+"&status="+Status);
    }
}
