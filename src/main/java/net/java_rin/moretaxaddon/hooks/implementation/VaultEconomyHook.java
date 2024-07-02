package net.java_rin.moretaxaddon.hooks.implementation;

import net.java_rin.moretaxaddon.MoreTaxAddon;
import net.java_rin.moretaxaddon.hooks.HooksId;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class VaultEconomyHook {

    private static Economy economy;

    public static void init() {
        economy = Bukkit.getServicesManager().getRegistration(Permission.class) == null ? null : Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
        if (MoreTaxAddon.getPlugin().getServer().getPluginManager().getPlugin(HooksId.VAULT) == null) {
            Bukkit.getLogger().info( ChatColor.RED + "Vault dependency not found. Some module might not work as expected!");
        } else {
            Bukkit.getLogger().info(ChatColor.GREEN + "Vault dependency found.");
        }
    }

    public static Economy getEconomy() {
        return economy;
    }
}
