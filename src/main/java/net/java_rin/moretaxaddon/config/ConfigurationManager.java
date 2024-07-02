package net.java_rin.moretaxaddon.config;

import net.java_rin.moretaxaddon.MoreTaxAddon;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigurationManager {

    private static final File configPath = new File(MoreTaxAddon.getPlugin().getDataFolder() + File.separator + "config.yml");
    private static FileConfiguration config;

    public static double BREAK_COST;
    public static String INSUFFICIENT_MONEY_MESSAGE;
    public static String SUCCESS_PAID_MESSAGE;

    public static void load() {
        if (!configPath.exists()) {
            MoreTaxAddon.getPlugin().saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configPath);
        BREAK_COST = config.getDouble("protection-stone.break-cost");
        INSUFFICIENT_MONEY_MESSAGE = config.getString("protection-stone.insufficient-money-message");
        SUCCESS_PAID_MESSAGE = config.getString("protection-stone.paid-message");
    }
}
