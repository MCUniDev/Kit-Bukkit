package org.mcuni.kit.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.mcuni.kit.Kit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import static org.bukkit.Bukkit.getLogger;

public class PlayerDeath implements Listener {
    public Kit plugin;

    /**
     * Constructor for the Player Death class.
     * @param plugin References to the main kit plugin class.
     */
    public PlayerDeath(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit] Player Death event handler started.");
    }

    /**
     * EventHandler for when the player dies.
     * @param event The event data.
     */
    @EventHandler(priority= EventPriority.HIGHEST)
    public String onPlayerDeath(EntityDeathEvent event){
        if (event.getEntityType() == EntityType.PLAYER) {
            Entity player = event.getEntity();
            String uuid = String.valueOf(player.getUniqueId());
            String location = String.valueOf(player.getLocation());
            String cause = String.valueOf(player.getLastDamageCause());
            try {
                if (plugin.getConfig().getBoolean("LogAPICalls")) {
                    getLogger().info("[Kit][PlayerDeath] Sending death data for player '" + player.getName() + "' with UUID '" + player.getUniqueId() + "'.");
                }
                URL url = new URL("https://kit.mcuni.org/api/"+plugin.APIVersion+"/death.php?key="+plugin.getConfig().getString("APIKey")+"&network="+plugin.getConfig().getString("NetworkID")+"&server="+plugin.getConfig().getString("ServerID")+"&uuid="+uuid+"&location="+location+"&cause="+cause);
                Scanner s = new Scanner(url.openStream());
                if (s.hasNextLine()) {
                    String response = s.nextLine();
                    if (response.equals("")) {
                        if (plugin.getConfig().getBoolean("LogAPICalls")) {
                            getLogger().info("[Kit][PlayerDeath] There was no response from the server.");
                        }
                        return "0";
                    } else {
                        if (plugin.getConfig().getBoolean("LogAPICalls")) {
                            getLogger().info("[Kit][PlayerDeath] Returned: " + response);
                        }
                        return response;
                    }
                }
            }
            catch(IOException ex) {
                getLogger().severe("[Kit][PlayerDeath] Fatal error.");
                getLogger().severe(Arrays.toString(ex.getStackTrace()));
            }
            if (plugin.getConfig().getBoolean("LogDebugInfo")) {
                getLogger().info("[DEBUG] https://kit.mcuni.org/api/v3/death.php?key=" + plugin.getConfig().getString("APIKey") + "&network=" + plugin.getConfig().getString("NetworkID") + "&server=" + plugin.getConfig().getString("ServerID") + "&uuid=" + uuid + "&location=" + location + "&cause=" + cause);
            }
        } else {
            if (plugin.getConfig().getBoolean("LogDebugInfo")) {
                getLogger().info("[DEBUG] Non-player entity died.");
            }
        }
        return "0";
    }
}