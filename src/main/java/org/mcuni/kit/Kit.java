package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcuni.kit.bStats.Metrics;
import org.mcuni.kit.commands.EventsCommands;
import org.mcuni.kit.commands.KitCommands;
import org.mcuni.kit.events.Carl;

import java.util.Objects;

public class Kit extends JavaPlugin {

    // Configs
    public String NetworkID = "PLYMOUTH";
    public String ServerID = "TEST";

    // Classes
    protected Broadcast broadcastClass;
    protected Carl carlClass;
    protected EventsCommands eventsCommandsClass;
    protected KitCommands kitCommandsClass;

    @Override
    public void onEnable() {
        showArt();

        loadClasses();
        loadEventHandlers();
        loadCommands();

        broadcastClass.broadcastTimer();
    }

    private void loadClasses() {
        new Metrics(this, 16474);
        broadcastClass = new Broadcast(this);
        carlClass = new Carl(this);
        eventsCommandsClass = new EventsCommands();
        kitCommandsClass = new KitCommands();
        Bukkit.getLogger().info("Loaded Classes.");
    }

    private void loadEventHandlers() {
        Bukkit.getServer().getPluginManager().registerEvents(carlClass, this);
        Bukkit.getLogger().info("Registered Event Handlers.");
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new KitCommands());
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new EventsCommands());
        Bukkit.getLogger().info("Registered Commands.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown login.
    }

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
