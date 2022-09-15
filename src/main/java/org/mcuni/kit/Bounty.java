package org.mcuni.kit;

public class Bounty extends Kit {
    public void initialise() {
        getLogger().info("Init: Bounty.");
    }

    public void shutdown() {
        getLogger().info("Disabled: Bounty.");
    }
}
