package org.mcuni.kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class Status {
    String NetworkID;
    String ServerID;

    public void initialise(String SID, String NID) {
        this.NetworkID = NID;
        this.ServerID = SID;
        sendStatus("START");
        getLogger().info("[Kit][Status] Init: Status.");
    }

    public void shutdown() {
        sendStatus("STOP");
        getLogger().info("Disabled: Status.");
    }

    public void sendStatus(String Status) {
        try {
            URL url = new URL("https://kit.mcuni.org/api/status/"+NetworkID+".php?"+ServerID+"status="+Status);
            new Scanner(url.openStream());
            getLogger().info("[Kit][Status] Sent Status Ping to MCUni Network.");
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Status] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
    }
}
