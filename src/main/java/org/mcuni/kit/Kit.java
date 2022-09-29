package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcuni.kit.commands.EventsCommands;
import org.mcuni.kit.commands.KitCommands;
import org.mcuni.kit.events.Carl;

import java.util.Objects;

/**
 * Main Kit class. Loads and enables all functionality.
 */
public class Kit extends JavaPlugin {

    // Configs
    public String NetworkID = "PLYMOUTH";
    public String ServerID = "SURVIVAL";
    public String ServerNickname = "UOPMC";
    public String UniversityName = "University of Plymouth";

    public FileConfiguration Config = this.getConfig();

    // Classes
    protected Broadcast broadcastClass;
    protected Carl carlClass;
    protected Status statusClass;
    protected ItemManager itemManagerClass;
    protected EventsCommands eventsCommandsClass;
    protected KitCommands kitCommandsClass;

    /**
     * Plugin startup logic. This is called when the plugin is enabled during server startup.
     */
    @Override
    public void onEnable() {
        showArt();

        loadClasses();
        loadEventHandlers();
        loadCommands();

        statusClass.sendStatus("START");
        broadcastClass.broadcastTimer();

        Bukkit.getLogger().info("[MCUni-Kit] Completed startup.");
    }

    /**
     * Loads the plugin's classes.
     */
    private void loadClasses() {
        broadcastClass = new Broadcast(this);
        carlClass = new Carl(this);
        itemManagerClass = new ItemManager(this);
        statusClass = new Status(this);
        eventsCommandsClass = new EventsCommands();
        kitCommandsClass = new KitCommands();
        Bukkit.getLogger().info("[MCUni-Kit] Loaded all Classes.");
    }

    /**
     * Loads and registers the plugin's event handlers.
     */
    private void loadEventHandlers() {
        Bukkit.getServer().getPluginManager().registerEvents(carlClass, this);
        Bukkit.getLogger().info("[MCUni-Kit] Registered Event Handlers.");
    }

    /**
     * Loads and registers the plugin's command handlers.
     */
    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new KitCommands());
        Bukkit.getLogger().info("[MCUni-Kit] Kit command executor registered.");
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new EventsCommands());
        Bukkit.getLogger().info("[MCUni-Kit] Event command executor registered.");
        Bukkit.getLogger().info("[MCUni-Kit] Registered Commands.");
    }

    /**
     * Plugin shutdown logic. This is called when the plugin is disabled during server shutdown.
     */
    @Override
    public void onDisable() {
        statusClass.sendStatus("STOP");
    }

    /**
     * Shows some lovely art when the server is starting up.
     */
    public void showArt() {
        getLogger().info("\n" +
                "                                    ,///////,                                   \n" +
                "                             //**//*////*////(/*////                            \n" +
                "                             *%//////*///(/*//#####(                            \n" +
                "                             ##//%%/*//((###%&##&%&/                            \n" +
                "                             /##/#%%%#%%#%&&%&&%#%%/                            \n" +
                "                             ((##(#((/(#%&%#%%%%%#%/                            \n" +
                "                             #%((((%###%#%%%&%&%%%#/                            \n" +
                "                             (#/##(###(/%%%%%%%#&%%(                            \n" +
                "                                 .((#%/(%%%%&%%                                 \n" +
                "                                       (%(                                      \n" +
                "                                                                                \n" +
                "         @@@@@@     @@@@@    @@@@@@@@@   @@@     @@@               @@@        \n" +
                "         @@@@@@    @@@@@@   @@@@         @@@     @@@   @@@@@@@@               \n" +
                "         @@@ @@@  @@@ @@@   @@@          @@@     @@@   @@@   @@@   @@@        \n" +
                "         @@@  @@@&@@  @@@   @@@@         @@@     @@@   @@@   @@@   @@@        \n" +
                "         @@@   @@@@   @@@    @@@@@@@@@    @@@@@@@@@    @@@   @@@   @@@        \n" +
                "                                                                                \n");
    }
}
