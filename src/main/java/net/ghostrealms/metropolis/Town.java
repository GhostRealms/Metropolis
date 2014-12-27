package net.ghostrealms.metropolis;

import net.ghostrealms.exception.InvalidPlotException;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Created by River on 29-Nov-14 18:41.
 * Town Object Class
 */

public class Town {

    private int id;
    private String name;
    private float balance;
    private UUID[] mayor;
    private Location spawnLocation;

    public Town() {

    }
}
