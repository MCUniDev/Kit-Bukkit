package org.mcuni.kit;

import org.bukkit.*;
import org.bukkit.inventory.*;

/**
 * This class handles custom items and crafting recipes
 */
public class ItemManager {
    public Kit plugin;

    /**
     * Constructor for the Status class.
     * @param plugin References to the main kit plugin class.
     */
    public ItemManager(Kit plugin) {
        this.plugin = plugin;
        Bukkit.getLogger().info("[MCUni-Kit][ItemManager] Registering ItemManager changes, please wait...");
        RegisterItems();
        RegisterRecipes();
        Bukkit.getLogger().info("[MCUni-Kit][ItemManager] Done");
        Bukkit.getLogger().info("[MCUni-Kit] ItemManager module started.");
    }

    /**
     * Registers the custom items into the game.
     */
    public void RegisterItems() {
        Bukkit.getLogger().info("[MCUni-Kit][ItemManager] Loaded 0 items.");
    }

    /**
     * Registers the custom recipes into the game.
     */
    public void RegisterRecipes() {
        RegisterCraftingRecipes();
        RegisterFurnaceRecipes();
    }

    /**
     * Registers the custom crafting recipes into the game.
     */
    public void RegisterCraftingRecipes() {
        Bukkit.getLogger().info("[MCUni-Kit][ItemManager] Loaded 0 crafting recipes.");
    }

    /**
     * Registers the custom furnace recipes into the game.
     */
    public void RegisterFurnaceRecipes() {
        // Rotten Flesh -> Leather
        FurnaceRecipe RottenFleshLeather = new FurnaceRecipe(NamespacedKey.minecraft("rottentoleather"), new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH, 0, 100);
        plugin.getServer().addRecipe(RottenFleshLeather);

        Bukkit.getLogger().info("[MCUni-Kit][ItemManager] Loaded 1 furnace recipes.");
    }
}
