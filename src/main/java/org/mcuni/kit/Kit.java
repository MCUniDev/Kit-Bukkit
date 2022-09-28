package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcuni.kit.commands.EventsCommands;
import org.mcuni.kit.commands.KitCommands;
import org.mcuni.kit.events.Carl;
import org.mcuni.kit.events.Whitelist;

import java.util.Objects;

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
    protected Whitelist whitelistClass;
    protected Status statusClass;
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
    }

    /**
     * Loads the plugin's classes.
     */
    private void loadClasses() {
        broadcastClass = new Broadcast(this);
        carlClass = new Carl(this);
        whitelistClass = new Whitelist(this);
        statusClass = new Status(this);
        eventsCommandsClass = new EventsCommands();
        kitCommandsClass = new KitCommands();
        Bukkit.getLogger().info("Loaded Classes.");
    }

    /**
     * Loads and registers the plugin's event handlers.
     */
    private void loadEventHandlers() {
        Bukkit.getServer().getPluginManager().registerEvents(carlClass, this);
        Bukkit.getServer().getPluginManager().registerEvents(whitelistClass, this);
        Bukkit.getLogger().info("Registered Event Handlers.");
    }

    /**
     * Loads and registers the plugin's command handlers.
     */
    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new KitCommands());
        Bukkit.getLogger().info("Kit command executor registered.");
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new EventsCommands());
        Bukkit.getLogger().info("Event command executor registered.");
        Bukkit.getLogger().info("Registered Commands.");
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
                "         (((((       ((((.       #@@@@,   (((     (((*               @@@        \n" +
                "         @@@@@(     @@@@@,   (@@@@@#%@@  .@@@     @@@%                /         \n" +
                "         @@@@@@    @@@@@@,  @@@@         .@@@     @@@%  %@@@@@@@@%   @@@        \n" +
                "         @@@ @@@  @@@ @@@,  @@@          .@@@     @@@%  %@@@   @@@.  @@@        \n" +
                "         @@@  @@@&@@  @@@,  @@@@          @@@     @@@,  %@@@   @@@,  @@@        \n" +
                "         @@@   @@@@   @@@,   #@@@@@@@@@   *@@@@@@@@@%   %@@@   @@@,  @@@        \n" +
                "                                                                                \n");
    }
}
