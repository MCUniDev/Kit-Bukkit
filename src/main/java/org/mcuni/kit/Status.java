package org.mcuni.kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class Status {

    public Kit plugin;

    public Status(Kit plugin) {
        this.plugin = plugin;
        sendStatus("START");
    }

    public void shutdown() {
        sendStatus("STOP");
        getLogger().info("Disabled: Status.");
    }

    public void sendStatus(String Status) {
        try {
            URL url = new URL("https://kit.mcuni.org/api/status/"+plugin.NetworkID+".php?"+plugin.ServerID+"status="+Status);
            new Scanner(url.openStream());
            getLogger().info("Sent Status Ping to MCUni Network.");
            getLogger().info("[DEBUG] https://kit.mcuni.org/api/status/"+plugin.NetworkID+".php?"+plugin.ServerID+"status="+Status);
        }
        catch(IOException ex) {
            getLogger().severe("[Kit][Status] Fatal error.");
            getLogger().severe(Arrays.toString(ex.getStackTrace()));
        }
    }
}
