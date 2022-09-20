package org.mcuni.kit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcuni.kit.commands.Events;
import org.mcuni.kit.commands.KitMain;

import java.util.Objects;

public class Kit extends JavaPlugin implements Plugin {

    String NetworkID = "PLYMOUTH";
    String ServerID = "GAMES-EVENTS";

    Config config = new Config();
    Carl carl = new Carl();
    Bounty bounty = new Bounty();
    Broadcast broadcast = new Broadcast();
    Status status = new Status();
    @Override
    public void onEnable() {
        getLogger().info("                                                                                \n" +
                "                                                                                \n" +
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
                "                                                                                \n" +
                "                                                                                \n" +
                "BUILD 3");
        bounty.initialise();
        broadcast.initialise(ServerID, NetworkID);
        carl.initialise();
        Bukkit.getServer().getPluginManager().registerEvents(carl, this);
        config.initialise();
        status.initialise(ServerID, NetworkID);
        Objects.requireNonNull(this.getCommand("kit")).setExecutor(new KitMain());
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new Events());
        getLogger().info("Loaded Kit.");
    }

    @Override
    public void onDisable() {
        bounty.shutdown();
        broadcast.shutdown();
        carl.shutdown();
        config.shutdown();
        status.shutdown();
        getLogger().info("Disabled Kit.");
    }
}
