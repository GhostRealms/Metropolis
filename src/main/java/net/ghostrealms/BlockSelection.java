package net.ghostrealms;

import net.ghostrealms.util.LocationStore;
import net.ghostrealms.util.SerializeStatus;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jamesraynor on 7/28/15.
 */
public class BlockSelection {
    public static final int MAX_DISTANCE = 200;
    public static final int SUCCESSFUL = 0;
    public static final int TO_LARGE = 1;
    public static final int NOT_SET = 2;
    public static final int DIFFERENT_WORLDS = 3;
    private static final HashMap<Player, BlockSelection> selections = new HashMap<Player, BlockSelection>();
    private LocationStore locations;
    private List<Block> storedLocation;

    private BlockSelection() {
    }

    /**
     * Gets the selection based on the player
     *
     * @param player
     * @return the selection of the player
     */
    public static BlockSelection getSelection(Player player) {
        if (!selections.containsKey(player)) {
            return new BlockSelection().putInList(player);
        }
        return selections.get(player);
    }

    /**
     * Gets the selection, or creates if.
     *
     * @param player
     * @return the selection
     */
    private BlockSelection putInList(Player player) {
        if (storedLocation == null) storedLocation = new ArrayList<Block>();
        selections.put(player, this);
        this.locations = new LocationStore();
        return this;
    }

    /**
     * Sets the first location
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.locations.setLocation(location);
    }

    /**
     * Sets the first location
     *
     * @param location
     */
    public void setLocation2(Location location) {
        this.locations.setLocation2(location);
    }

    /**
     * Removes all the values inside of the last selection
     */
    public void reset() {
        for (int i = 0; i < selections.size(); i++) selections.remove(i);
    }

    /**
     * @return the blocks that are selected
     */
    public List<Block> getSelectedBlocks() {
        return storedLocation;
    }

    /**
     * populates the list of locations.
     * @return status code
     */
    public SerializeStatus serialize() {
        if (!locations.areSet()) return SerializeStatus.NOT_SET;
        if (!locations.equalWorlds()) return SerializeStatus.DIFFERENT_WORLDS;
        if (locations.distance() > MAX_DISTANCE) return SerializeStatus.TO_LARGE;

        int x1 = locations.getLocation().getBlockX();
        int x2 = locations.getLocation2().getBlockX();
        int y1 = locations.getLocation().getBlockY();
        int y2 = locations.getLocation2().getBlockY();
        int z1 = locations.getLocation().getBlockZ();
        int z2 = locations.getLocation2().getBlockZ();

        int minX, maxX, minY, maxY, minZ, maxZ;

        if (x1 < x2) {
            minX = x1;
            maxX = x2;
        } else {
            minX = x2;
            maxX = x1;
        }
        if (y1 < y2) {
            minY = y1;
            maxY = y2;
        } else {
            minY = y2;
            maxY = y1;
        }
        if (z1 < z2) {
            minZ = z1;
            maxZ = z2;
        } else {
            minZ = z2;
            maxZ = z1;
        }

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    storedLocation.add(locations.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return SerializeStatus.SUCCESSFUL;
    }
}
