package net.ghostrealms.exception;

import org.bukkit.plugin.Plugin;

/**
 * Created by River on 2/1/2015.
 */
public class InvalidDependencyException extends RealmsException {
    
    public InvalidDependencyException() { super(); }
    
    public InvalidDependencyException(Plugin plugin) {
        super("Unable to load: " + plugin.getName() + ":" + plugin.getDescription().getVersion());
    }
}
