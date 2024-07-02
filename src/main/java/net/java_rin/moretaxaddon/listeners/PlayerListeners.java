package net.java_rin.moretaxaddon.listeners;

import dev.espi.protectionstones.event.PSRemoveEvent;
import net.java_rin.moretaxaddon.MoreTaxAddon;
import net.java_rin.moretaxaddon.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerListeners implements Listener {

    @EventHandler
    public void ps_rm_e(PSRemoveEvent e) {
        Player p = e.getPlayer();
        double pb = MoreTaxAddon.economy.getBalance(p);

        if (pb < ConfigurationManager.BREAK_COST) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigurationManager.INSUFFICIENT_MONEY_MESSAGE));
            e.setCancelled(true);
            return;
        }

        // todo: fix this event fired twice
        MoreTaxAddon.economy.withdrawPlayer(p, ConfigurationManager.BREAK_COST);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigurationManager.SUCCESS_PAID_MESSAGE.replace("{amount}", String.valueOf(ConfigurationManager.BREAK_COST))));
    }
}
