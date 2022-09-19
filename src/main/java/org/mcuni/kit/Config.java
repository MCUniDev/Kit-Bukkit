package org.mcuni.kit;

import static org.bukkit.Bukkit.getLogger;

public class Config {
    public void initialise() {
        getLogger().info("Init: Config.");
    }

    public void shutdown() {
        getLogger().info("Disabled: Config.");
    }
}
