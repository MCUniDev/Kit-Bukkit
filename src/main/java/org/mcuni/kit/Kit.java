package org.mcuni.kit;

import org.bukkit.plugin.java.JavaPlugin;

public class Kit extends JavaPlugin {

    String ServerID = "PLYMOUTH";

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
                "                                                                                ");
        bounty.initialise();
        broadcast.initialise(ServerID);
        status.initialise(ServerID);
        getLogger().info("Loaded Kit.");
    }

    @Override
    public void onDisable() {
        bounty.shutdown();
        broadcast.shutdown();
        status.shutdown();
        getLogger().info("Disabled Kit.");
    }
}
