package net.ghostrealms;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by River on 2/1/2015.
 */
public class WEapi {
    
    private final WorldEditPlugin instance;
    
    public WEapi (WorldEditPlugin instance) {
        this.instance = instance;
    }
    
    public void selectChunk(Player player) {
        selectChunk(player.getLocation());
    }
    
    public void selectChunk(Location location) {
        
    }
}
