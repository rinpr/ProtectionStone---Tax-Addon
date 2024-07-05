package net.java_rin.moretaxaddon.hooks.implementation;

import dev.espi.protectionstones.PSProtectBlock;
import dev.espi.protectionstones.PSRegion;
import dev.espi.protectionstones.ProtectionStones;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class ProtectionStoneHook {

    /**
     * Check whether a given block is a protection block, without check if its actually protects a region.
     * @param block the block to look at
     * @return whether the block is a protection block item.
     */
    public static boolean isPSBlock(Block block) {
        // todo: fix bugs where same block material can still be placed
        PSProtectBlock blockOptions = ProtectionStones.getBlockOptions(block);
        return blockOptions != null;
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
