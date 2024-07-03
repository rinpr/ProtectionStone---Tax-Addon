package net.java_rin.moretaxaddon;

import net.java_rin.moretaxaddon.command.MoreTaxCommand;
import net.java_rin.moretaxaddon.config.ConfigurationManager;
import net.java_rin.moretaxaddon.hooks.implementation.VaultEconomyHook;
import net.java_rin.moretaxaddon.listeners.PlayerListeners;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MoreTaxAddon extends JavaPlugin {

    private static MoreTaxAddon plugin;
    public static Economy economy;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getConsoleSender().sendMessage("----------------------------------");
        getServer().getConsoleSender().sendMessage("| ProtectionStone - MoreTaxAddon |");
        getServer().getConsoleSender().sendMessage("|            by Java_Rin         |");
        getServer().getConsoleSender().sendMessage("----------------------------------");
        VaultEconomyHook.init();
        economy = VaultEconomyHook.getEconomy();
        ConfigurationManager.load();
        registerListener();
        registerCommand();
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PlayerListeners(), plugin);
    }

    private void registerCommand() { Objects.requireNonNull(this.getCommand("moretaxaddon")).setExecutor(new MoreTaxCommand()); }

    public static MoreTaxAddon getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
