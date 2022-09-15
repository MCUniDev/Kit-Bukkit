package org.mcuni.kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class Status {
    int ServerID;

    public void initialise(int SID) {
        this.ServerID = SID;
        sendStatus("START");
        getLogger().info("Init: Status.");
    }

    public void shutdown() {
        sendStatus("STOP");
        getLogger().info("Disabled: Status.");
    }

    public void sendStatus(String Status) {
        try {
            URL url = new URL("https://kit.mcuni.org/status/"+ServerID+".json?status="+Status);
            new Scanner(url.openStream());
            getLogger().info("[Kit][Status] Sent Status Ping to MCUni Network.");
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Status] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
    }
}
