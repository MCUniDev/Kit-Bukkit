package org.mcuni.kit.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
            EntityDamageEvent deathCause = player.getLastDamageCause();
            String cause;
            if (deathCause.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                    deathCause.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ||
                    deathCause.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
                Entity entity = (((EntityDamageByEntityEvent)deathCause).getDamager());
                if (entity instanceof Player) {
                    Player killerPlayer = (Player)entity;
                    cause = "Murdered by " + killerPlayer.getName();
                }
                else {
                    Monster killerMob = (Monster)entity;
                    cause = killerMob.getType().toString();
                    cause = cause.substring(0,1).toUpperCase() + cause.substring(1).toLowerCase();
                }
            } else {
                cause = GetDeathReason(deathCause.getCause());
            }
            cause = cause.replace(" ", "%20");
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

    private String GetDeathReason(EntityDamageEvent.DamageCause lastDamageCause) {
        if (lastDamageCause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            return "Exploded";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.LAVA) {
            return "Lava damage";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.CONTACT) {
            return "Touched a block with spikes";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.CRAMMING) {
            return "Entity Cramming";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
            return "Dragon Breath";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.DROWNING) {
            return "Drowned";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            return "Exploded";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.FALL) {
            return "Fall damage";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
            return "Hit by a falling block";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.FIRE) {
            return "Burnt by fire";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.FIRE_TICK) {
            return "Burnt by fire";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
            return "Flew into a wall";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.FREEZE) {
            return "Froze to death";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.LIGHTNING) {
            return "Struck by lightning";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.MAGIC) {
            return "Potion or spell";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.POISON) {
            return "Poison";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.PROJECTILE) {
            return "Hit by a projectile";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.SONIC_BOOM) {
            return "Warden's Sonic Boom";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.STARVATION) {
            return "Starvation";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.SUFFOCATION) {
            return "Suffocation";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.SUICIDE) {
            return "Self inflicted";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.THORNS) {
            return "Thorns enchantment";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.VOID) {
            return "Fell through the world";
        } else if (lastDamageCause == EntityDamageEvent.DamageCause.WITHER) {
            return "Wither potion";
        } else {
            return "Unknown";
        }
    }
}