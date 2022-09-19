package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import static org.bukkit.Bukkit.getLogger;

public class Broadcast {
    String ServerID;
    private String broadcasts;

    public void initialise(String SID) {
        ServerID = SID;
        broadcastTimer();
        getLogger().info("Init: Broadcast.");
    }

    public void shutdown() {
        getLogger().info("Disabled: Broadcast.");
    }

    private void broadcastTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                broadcasts = getBroadcast();
                if (broadcasts != null) {
                    doBroadcast(broadcasts);
                } else {
                    getLogger().info("[Kit][Broadcast] No pending messages - not sending a broadcast.");
                }
            }
        }, 30*60*1000, 30*60*1000);
    }

    private void doBroadcast(String broadcasts) {
        if (broadcasts != null) {
            Bukkit.broadcastMessage(ChatColor.GOLD + "[MCUni Broadcast] > " + ChatColor.YELLOW + broadcasts);
        }
    }

    public String getBroadcast() {
        try {
            getLogger().info("[Kit][Broadcast] Fetched remote broadcasts.");
            URL url = new URL("http://kit.mcuni.org/api/broadcast/"+ServerID+".json");
            Scanner s = new Scanner(url.openStream());
            String BroadcastString = s.nextLine();
            if (BroadcastString.equals("")) {
                getLogger().info("[Kit][Broadcast] There are no remote broadcasts.");
                return null;
            } else {
                getLogger().info("[Kit][Broadcast] Fetched: " + BroadcastString);
                return BroadcastString;
            }
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Broadcast] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }
}
