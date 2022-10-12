package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcuni.kit.commands.EventsCommands;
import org.mcuni.kit.commands.HelpCommands;
import org.mcuni.kit.commands.KitCommands;
import org.mcuni.kit.events.Carl;
import org.mcuni.kit.events.StatusStart;
import org.mcuni.kit.events.Whitelist;

import java.util.Objects;

/**
 * Main Kit class. Loads and enables all functionality.
 */
public class Kit extends JavaPlugin {

    // Variables
    public boolean shutdownPing = true;
    public String APIVersion = "v3";

    // Classes
    protected Broadcast broadcastClass;
    protected Carl carlClass;
    protected StatusStart statusStartClass;
    protected Status statusClass;
    protected ItemManager itemManagerClass;
    protected Whitelist whitelistClass;

    /**
     * Plugin startup logic. This is called when the plugin is enabled during server startup.
     * Calls functions to load config, classes, event handlers, commands, and run startup actions.
     * @see #loadConfig() Loads the configuration.
     * @see #loadClasses() Loads all modules.
     * @see #loadEventHandlers() Loads the event handlers.
     * @see #loadCommands() Loads the commands.
     */
    @Override
    public void onEnable() {
        showArt();

        loadConfig();
        loadClasses();

        statusClass.sendStatus(1);

        loadEventHandlers();
        loadCommands();

        Bukkit.getLogger().info("[MCUni-Kit] Completed startup.");
    }

    /**
     * Loads the plugin's configuration
     * @see #checkConfig()
     */
    private void loadConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        checkConfig();
    }

    /**
     * Checks that the plugin's configuration is filled out and working correctly.
     */
    private void checkConfig() {
        if (getConfig().getString("NetworkID") == null ||
                getConfig().getString("ServerID") == null ||
                getConfig().getString("APIKey") == null) {
            Bukkit.getLogger().severe("[MCUni-Kit] Required configuration values are empty. Please complete the configuration before continuing.");
            shutdownPing = false;
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    /**
     * Loads the plugin's classes.
     */
    private void loadClasses() {
        broadcastClass = new Broadcast(this);
        carlClass = new Carl(this);
        itemManagerClass = new ItemManager(this);
        statusClass = new Status(this);
        statusStartClass = new StatusStart(this);
        whitelistClass = new Whitelist(this);
        Bukkit.getLogger().info("[MCUni-Kit] Loaded all Classes.");
    }

    /**
     * Loads and registers the plugin's event handlers.
     */
    private void loadEventHandlers() {
        Bukkit.getServer().getPluginManager().registerEvents(carlClass, this);
        Bukkit.getServer().getPluginManager().registerEvents(statusStartClass, this);
        Bukkit.getServer().getPluginManager().registerEvents(whitelistClass, this);
        Bukkit.getLogger().info("[MCUni-Kit] Registered Event Handlers.");
    }

    /**
     * Loads and registers the plugin's command handlers.
     */
    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new KitCommands());
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new EventsCommands());
        Objects.requireNonNull(this.getCommand("help")).setExecutor(new HelpCommands(this));
        Bukkit.getLogger().info("[MCUni-Kit] Registered Command Executors.");
    }

    /**
     * Plugin shutdown logic. This is called when the plugin is disabled during server shutdown.
     */
    @Override
    public void onDisable() {
        Bukkit.broadcastMessage(ChatColor.GREEN + "Carl > Goodbye!");
        if (shutdownPing) {
            statusClass.sendStatus(0);
        }
    }

    /**
     * Shows some lovely art when the server is starting up.
     */
    private void showArt() {
        getLogger().info("\n" +
                "         @@@@@@     @@@@@    @@@@@@@@@   @@@     @@@               @@@        \n" +
                "         @@@@@@    @@@@@@   @@@@         @@@     @@@   @@@@@@@@               \n" +
                "         @@@ @@@  @@@ @@@   @@@          @@@     @@@   @@@   @@@   @@@        \n" +
                "         @@@  @@@&@@  @@@   @@@@         @@@     @@@   @@@   @@@   @@@        \n" +
                "         @@@   @@@@   @@@    @@@@@@@@@    @@@@@@@@@    @@@   @@@   @@@        \n" +
                "                                                                              \n" +
                "         Now starting MCUni-Kit for Bukkit "+getDescription().getAPIVersion()+" - Version "+getDescription().getVersion()+"\n");
    }
}
