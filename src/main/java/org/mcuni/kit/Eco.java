package org.mcuni.kit;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * This class handles the server's economy links.
 */
public class Eco {

    public Kit plugin;
    private Economy eco = null;

    /**
     * Constructor for the Economy class.
     * @param plugin References to the main kit plugin class.
     */
    public Eco(Kit plugin) {
        this.plugin = plugin;
        if (!setupEconomy() ) {
            plugin.getLogger().severe("[MCUni-Kit][Eco] - This plugin requires Vault to be installed.");
            plugin.getLogger().severe("[MCUni-Kit] Please install Vault. Disabling...");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        GetBalances();
        Bukkit.getLogger().info("[MCUni-Kit] Eco module started.");
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return true;
    }

    public void GetBalances() {
        OfflinePlayer[] OfflinePlayers = Bukkit.getOfflinePlayers();

        String[][] balances = new String[OfflinePlayers.length][1];
        Bukkit.getLogger().info("[MCUni-Kit][Eco] Offline players: " + OfflinePlayer[0].getPlayer());

        int i=0;
        for (i=0; i < OfflinePlayers.length ; i++) {
            Player player = OfflinePlayers[i].getPlayer();
            balances[i][0] = player.getName();
            balances[i][1] = String.valueOf(eco.getBalance(player.getName()));
            Bukkit.getLogger().info("[MCUni-Kit][Eco] Balances: " + Arrays.deepToString(balances));
        }

        if (i==0) {
            Bukkit.getLogger().info("[MCUni-Kit][Eco] No players offline");
        }
    }
}
