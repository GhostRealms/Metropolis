package net.ghostrealms;

import net.ghostrealms.util.ChunkStore;
import net.ghostrealms.util.SerializeStatus;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jamesraynor on 7/31/15.
 */
public class ChunkSelection {
    private static final HashMap<Player, ChunkSelection> selections = new HashMap<Player, ChunkSelection>();
    private ChunkStore chunkStore;
    private List<Chunk> chunkList;

    /**
     * Gets the selection based on the player
     *
     * @param player
     * @return the selection of the player
     */
    public static ChunkSelection getSelection(Player player) {
        if (!selections.containsKey(player)) {
            return new ChunkSelection().putInList(player);
        }
        return selections.get(player);
    }

    /**
     * Gets the selection, or creates if.
     *
     * @param player
     * @return the selection
     */
    private ChunkSelection putInList(Player player) {
        if (this.chunkStore == null) this.chunkStore = new ChunkStore(player);
        if (this.chunkList == null) this.chunkList = new ArrayList<Chunk>();
        return this;
    }

    /**
     * Adds a chunk if it's not already added, or already mounted in the 'ChunkStore' class
     *
     * @param location
     * @return status code
     */
    public SerializeStatus addChunk(Location location) {
        if (this.chunkList.contains(location)) return SerializeStatus.ALREADY_SET;
        if (!chunkList.contains(chunkStore.getChunk())) chunkList.add(chunkStore.getChunk());
        else return SerializeStatus.ALREADY_SET;
        if (chunkStore.setLocation(location) != SerializeStatus.ALREADY_SET) chunkList.add(chunkStore.getChunk());
        return SerializeStatus.SUCCESSFUL;
    }
}
