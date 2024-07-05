package net.java_rin.moretaxaddon.config;

import net.java_rin.moretaxaddon.MoreTaxAddon;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigurationManager {

    private static final File configPath = new File(MoreTaxAddon.getPlugin().getDataFolder() + File.separator + "config.yml");
    private static FileConfiguration config;

    public static double BREAK_COST;
    public static String INSUFFICIENT_MONEY_MESSAGE;
    public static String SUCCESS_PAID_MESSAGE;
    public static String NOT_ALLOWED_PLACE_MESSAGE;
    public static Set<World> NOT_ALLOWED_WORLDS;

    public static void load() {
        if (!configPath.exists()) {
            MoreTaxAddon.getPlugin().saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configPath);
        BREAK_COST = config.getDouble("protection-stone.break-cost");
        INSUFFICIENT_MONEY_MESSAGE = config.getString("protection-stone.insufficient-money-message");
        SUCCESS_PAID_MESSAGE = config.getString("protection-stone.paid-message");
        NOT_ALLOWED_PLACE_MESSAGE = config.getString("force-protection-stone.not-allowed-place-message");
        NOT_ALLOWED_WORLDS = getWorldsFromStringList(config.getStringList("force-protection-stone.not-allowed-world"));
    }

    /**
     * Retrieves a set of worlds based on a list of world names.
     *
     * @param worldStrings List of world names.
     * @return A set of World objects corresponding to the provided names.
     */
    private static Set<World> getWorldsFromStringList(List<String> worldStrings) {
        Set<World> worlds = new HashSet<>();
        for (String worldName : worldStrings) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                worlds.add(world);
            } else {
                Bukkit.getLogger().warning("Error loading world: " + worldName);
            }
        }
        return worlds;
    }
}
