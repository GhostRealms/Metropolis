package net.ghostrealms.util;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * Created by jamesraynor on 7/30/15.
 */
public class LocationStore {
    private Location location;
    private Location location2;

    public LocationStore() {
        this.location = null;
        this.location2 = null;
    }

    /**
     * Gets the first location
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the first location
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the second location
     *
     * @return
     */
    public Location getLocation2() {
        return location2;
    }

    /**
     * Sets the second location
     *
     * @param location2
     */
    public void setLocation2(Location location2) {
        this.location2 = location2;
    }

    /**
     * Used to test if both location are set or not
     *
     * @return
     */
    public boolean areSet() {
        return (location != null && location2 != null);
    }


    /**
     * Resets the locations
     */
    public void purge() {
        this.location = null;
        this.location2 = null;
    }

    /**
     * @return whether or not the worlds are the same
     */
    public boolean equalWorlds() {
        return location.getWorld() == location2.getWorld();
    }

    /**
     * @return the distance of the two locations in an integer
     */
    public int distance() {
        return (int) location.distance(location2);
    }

    /**
     * @return the world if they are equal, otherwise throws an exception
     */
    public World getWorld() {
        if (equalWorlds()) {
            return location.getWorld();
        }
        try {
            throw new Exception("Worlds are different");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
