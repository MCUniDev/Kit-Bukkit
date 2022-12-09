package org.mcuni.kit;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Arrays;

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
        String[][] balances = GetBalances();
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

    public String[][] GetBalances() {
        try {
            OfflinePlayer[] Players = Bukkit.getOfflinePlayers();

            int i = 0;

            String[][] balances = new String[Players.length][2];
            Bukkit.getLogger().info("[MCUni-Kit][Eco] Offline players: " + Players[i].getUniqueId() + " Balance: " + eco.getBalance(Players[i]));

            for (i = 0; i < Players.length; i++) {
                Bukkit.getLogger().info("[MCUni-Kit][Eco] Player: " + i);
                balances[i][0] = String.valueOf(Players[i].getUniqueId());
                balances[i][1] = String.valueOf(eco.getBalance(Players[i]));
                Bukkit.getLogger().info("[MCUni-Kit][Eco] Balances: " + Arrays.deepToString(balances));
            }

            if (i == 0) {
                Bukkit.getLogger().info("[MCUni-Kit][Eco] No players offline");
            }

            return balances;

        } catch (Exception e) {
            Bukkit.getLogger().severe("[MCUni-Kit][Eco] Unable to get balances.");
            e.printStackTrace();
        }
        return null;
    }
}
