package net.ghostrealms;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import java.util.UUID;

/**
 * Created by River on 2/1/2015.
 */

public class WGapi {
    
    private final WorldGuardPlugin instance;
    
    public WGapi(WorldGuardPlugin instance) {
        this.instance = instance;
    }
    
    public void registerProtectedRegion(UUID owner) {

    }
}
