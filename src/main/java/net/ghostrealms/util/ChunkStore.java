package net.ghostrealms.util;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This class takes in a location, and converts it to a readable chunk for easy use
 * Created by jamesraynor on 7/31/15.
 */
public class ChunkStore {
    private Location location;
    private Player player;
    private Chunk chunk;

    public ChunkStore(Player player) {
        this.location = null;
        this.player = player;
    }

    /**
     * Gets the chunk based on the current location
     *
     * @return chunk
     */
    public Chunk getChunk() {
        if (location != null) {
            chunk = player.getWorld().getChunkAt(location);
            return chunk;
        }
        return null;
    }

    /**
     * Sets the location to current location, if chunks and location is different
     *
     * @param location
     */
    public SerializeStatus setLocation(Location location) {
        if (this.location != location && this.chunk != location.getChunk()) {
            this.location = location;
            return SerializeStatus.SUCCESSFUL;
        }
        return SerializeStatus.ALREADY_SET;
    }
}
