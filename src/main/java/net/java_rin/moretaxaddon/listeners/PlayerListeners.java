package net.java_rin.moretaxaddon.listeners;

import dev.espi.protectionstones.event.PSBreakProtectBlockEvent;
import net.java_rin.moretaxaddon.MoreTaxAddon;
import net.java_rin.moretaxaddon.config.ConfigurationManager;
import net.java_rin.moretaxaddon.hooks.implementation.ProtectionStoneHook;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void ps_rm_e(PSBreakProtectBlockEvent e) {
        Player p = e.getPlayer();
        double pb = MoreTaxAddon.economy.getBalance(p);

        if (pb < ConfigurationManager.BREAK_COST) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigurationManager.INSUFFICIENT_MONEY_MESSAGE));
            e.setCancelled(true);
            return;
        }

        MoreTaxAddon.economy.withdrawPlayer(p, ConfigurationManager.BREAK_COST);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigurationManager.SUCCESS_PAID_MESSAGE.replace("{amount}", String.valueOf(ConfigurationManager.BREAK_COST))));
    }

    @EventHandler
    public void bpe(BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
        if (!ConfigurationManager.NOT_ALLOWED_WORLDS.contains(b.getLocation().getWorld())) return;
        Player p = e.getPlayer();
        if (ProtectionStoneHook.isInPSRegion(b.getLocation())) return;
        if (!ProtectionStoneHook.isPSBlock(b, e.getItemInHand())) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigurationManager.NOT_ALLOWED_PLACE_MESSAGE));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void bbe(BlockBreakEvent e) {
        Block b = e.getBlock();
        if (!ConfigurationManager.NOT_ALLOWED_WORLDS.contains(b.getLocation().getWorld())) return;
        Player p = e.getPlayer();
        if (ProtectionStoneHook.isInPSRegion(b.getLocation())) return;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigurationManager.NOT_ALLOWED_PLACE_MESSAGE));
        e.setCancelled(true);
    }
}
