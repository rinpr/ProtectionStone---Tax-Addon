package net.java_rin.moretaxaddon.hooks.implementation;

import dev.espi.protectionstones.PSProtectBlock;
import dev.espi.protectionstones.PSRegion;
import dev.espi.protectionstones.ProtectionStones;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ProtectionStoneHook {

    public static boolean isPSBlock(Block block) {
        PSProtectBlock blockOptions = ProtectionStones.getBlockOptions(block);
        if (blockOptions == null) return false;
        return ProtectionStones.isProtectBlock(block);
    }

    public static ItemStack getPsBlockItem(Block block) {
        if (!ProtectionStones.isProtectBlock(block)) return null;
        PSProtectBlock ps = ProtectionStones.getBlockOptions(block);
        return ProtectionStones.createProtectBlockItem(ps);
    }

    public static ItemStack getPSBlockItem(PSRegion region) {
        return getPsBlockItem(region.getProtectBlock());
    }

}
