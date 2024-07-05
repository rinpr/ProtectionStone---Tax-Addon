package net.java_rin.moretaxaddon.hooks.implementation;

import dev.espi.protectionstones.PSProtectBlock;
import dev.espi.protectionstones.PSRegion;
import dev.espi.protectionstones.ProtectionStones;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ProtectionStoneHook {

    /**
     * Check whether a given block is a protection block, without check if its actually protects a region.
     * @param block the block to check
     * @param hand the item to check
     * @return whether the block is a protection block item.
     */
    public static boolean isPSBlock(@NotNull Block block, @NotNull ItemStack hand) {
        // check if the block is a protection stone
        if (!ProtectionStones.isProtectBlockType(block)) return false;
        PSProtectBlock blockOptions = ProtectionStones.getBlockOptions(block);
        // check if the item was created by protection stones (stored in custom tag)
        return !blockOptions.restrictObtaining || ProtectionStones.isProtectBlockItem(hand, true);
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
