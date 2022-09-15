package org.mcuni.kit;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static org.bukkit.Bukkit.getLogger;

public class Broadcast {
    int ServerID;
    public void initialise(int SID) {
        ServerID = SID;
        String Broadcasts = getBroadcast();
        broadcastTimer(Broadcasts);
        getLogger().info("Init: Broadcast.");
    }

    public void shutdown() {
        getLogger().info("Disabled: Broadcast.");
    }

    private void broadcastTimer(String Broadcasts) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                doBroadcast(Broadcasts);
            }
        }, 2*60*1000, 2*60*1000);
    }

    private void doBroadcast(String broadcasts) {
        if (broadcasts != null) {
            Bukkit.broadcastMessage("[MCUni Broadcast] > " + broadcasts);
        }
    }

    public String getBroadcast() {
        try {
            getLogger().info("[Kit][Broadcast] Fetched remote broadcasts.");
            URL url = new URL("https://kit.mcuni.org/broadcast/"+ServerID+".json");
            Scanner s = new Scanner(url.openStream());
            return s.toString();
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Broadcast] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
        return null;
    }
}
