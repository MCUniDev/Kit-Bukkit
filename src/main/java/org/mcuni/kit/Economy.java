package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * This class handles the server's economy links.
 */
public class Economy {

    public Kit plugin;
    private static Economy econ = null;

    /**
     * Constructor for the Economy class.
     * @param plugin References to the main kit plugin class.
     */
    public Economy(Kit plugin) {
        this.plugin = plugin;
        if (!setupEconomy() ) {
            plugin.getLogger().severe(String.format("[MCUni-Kit][Economy] - This plugin requires Vault to be installed."));
            plugin.getLogger().severe(String.format("[MCUni-Kit] Please install Vault. Disabling..."));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        GetBalances();
        Bukkit.getLogger().info("[MCUni-Kit] Economy module started.");
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public void GetBalances() {
        Collection<? extends Player> OPC = Bukkit.getOnlinePlayers();
        OfflinePlayer[] OfflinePlayers = Bukkit.getOfflinePlayers();

        Array[] OnlinePlayers = OPC.toArray(new Array[OPC.size()]);

        for (int i=0; i < OnlinePlayers.length ; i++) {
            Bukkit.getLogger().info("[MCUni-Kit] USER BALANCE: "+getBalance());
        }
    }
}
