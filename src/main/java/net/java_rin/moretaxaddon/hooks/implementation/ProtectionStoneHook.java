package net.java_rin.moretaxaddon.hooks.implementation;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import dev.espi.protectionstones.PSMergedRegion;
import dev.espi.protectionstones.PSProtectBlock;
import dev.espi.protectionstones.PSRegion;
import dev.espi.protectionstones.ProtectionStones;
import dev.espi.protectionstones.utils.WGUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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

    /**
     * Retrieves a Protection Stone Item representing the ProtectionStones block associated with the given Block.
     *
     * @param block The Block to retrieve the ItemStack for.
     * @return The Protection Stone Item representing the ProtectionStones block, or null if the block is not a protected block.
     */
    public static ItemStack getPsBlockItem(Block block) {
        if (!ProtectionStones.isProtectBlock(block)) return null;
        PSProtectBlock ps = ProtectionStones.getBlockOptions(block);
        return ProtectionStones.createProtectBlockItem(ps);
    }

    /**
     * Retrieves a Protection Stone Item representing the ProtectionStones block associated with the given PSRegion.
     *
     * @param region The PSRegion to retrieve the ItemStack for.
     * @return The Protection Stone Item representing the ProtectionStones block, or null if the region's block is not protected.
     */
    public static ItemStack getPSBlockItem(PSRegion region) {
        return getPsBlockItem(region.getProtectBlock());
    }

    /**
     * Checks if the given location is within a PS (ProtectionStones) region.
     *
     * @param location The location to check.
     * @return {@code true} if the location is within a PS region, otherwise {@code false}.
     */
    public static boolean isInPSRegion(Location location) {
        PSRegion r = PSRegion.fromLocation(location);
        return r != null;
    }
}
