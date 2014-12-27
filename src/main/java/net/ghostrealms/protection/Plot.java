package net.ghostrealms.protection;

import net.ghostrealms.Metro;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * Created by River on 30-Nov-14.
 * Plot System
 */
public class Plot {

    private int id;
    private int x;
    private int y;
    private World world;
    private List<String> owner = new ArrayList<String>();
    private List<String> access = new ArrayList<String>();
    private List<String> use = new ArrayList<String>();
    private List<String> edit = new ArrayList<String>();
    private List<String> banned = new ArrayList<String>();
    private char[] flags;


    /**
     * Plot constructor - Register a new Plot Object
     * @author River Marmorstein
     * @param world World of the Plot
     * @param x chunk x coordinate
     * @param y chunk y coordinate
     */
    public Plot(World world, int x, int y) {

    }

    public Plot(Chunk chunk, UUID owner) {
        world = chunk.getWorld();
        x = chunk.getX();
        y = chunk.getZ();
        this.owner.add(owner.toString());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public boolean hasPermission(PermissionType type, Player player) {
        return false;
    }

    /**
     * Get a plot by a Bukkit location
     * @author River Marmorstein
     * @param location of the plot to receive
     * @return plot object containing provided location
     */
    public static Plot getPlotByWorldLocation(Location location) {
        int chunkX = location.getChunk().getX();
        int chunkZ = location.getChunk().getZ();

        try {

            Statement stmt = Metro.getConnection().createStatement();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * Convert stored permission values to their object representation
     * @author River Marmorstein
     * @param string from storage engine
     * @return List of permissible values
     */
    private List<String> conversion(String string) {
        String[] split = string.split(",");
        return Arrays.asList(split);
    }

}
