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
    private String broadcasts;

    public void initialise(int SID) {
        ServerID = SID;
        this.broadcasts = getBroadcast();
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
                doBroadcast();
            }
        }, 2*60*1000, 2*60*1000);
    }

    private void doBroadcast() {
        if (this.broadcasts != null) {
            Bukkit.broadcastMessage("[MCUni Broadcast] > " + this.broadcasts);
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
