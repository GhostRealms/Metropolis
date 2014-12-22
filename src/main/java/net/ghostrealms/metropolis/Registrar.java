package net.ghostrealms.metropolis;

import net.ghostrealms.exception.InvalidPlotException;
import net.ghostrealms.protection.Plot;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by River on 30-Nov-14.
 * Registration of Residents, Towns, and States
 */
public class Registrar {

    private final World main;
    private final Metro _instance;
    private final Connection conn = Metro.getConnection();

    private Map<Integer, Plot> integerPlotMap;
    private Map<Integer, Town> integerTownMap;
    private Map<Integer, State> integerStateMap;
    private Map<UUID, Resident> uuidResidentMap;

    private List<Resident> residentCache;
    private List<Plot> plotCache;

    private PreparedStatement preparedStatementCreateNewResident;
    private PreparedStatement preparedStatementCreateNewTown;
    private PreparedStatement preparedStatementCreateNewState;
    private PreparedStatement preparedStatementRegisterNewPlot;

    private String sqlPrepareCreateTown;
    private String sqlPrepareCreateState;
    private String sqlPrepareNuResident;
    private String sqlPrepareRegisterPlot;


    /**
     * Invoke a new instance of the world registrar
     * @param w world to invoke the registrar
     */
    public Registrar(World w, Metro _instance) {
        this._instance = _instance;
        main = w;
        sqlPrepareNuResident = _instance.getConfig().getString("database.statements.new_resident");
        sqlPrepareCreateState = _instance.getConfig().getString("database.statements.create_state");
        sqlPrepareCreateTown = _instance.getConfig().getString("database.statements.create_town");
        sqlPrepareRegisterPlot = _instance.getConfig().getString("database.statements.register_plot");
    }

    public void registerNewResident(UUID id) {

    }

    /**
     * Return a plot object from specified plot (x,y) coordinate pair
     * @author River Marmorstein
     * @param x Plot x coordinate
     * @param y Plot y coordinate
     * @return Plot object
     */
    public Plot getPlot(int x, int y) {

        ResultSet results;

        for(Plot plot : plotCache) {
            if(plot.getX() == x && plot.getY() == y) {
                return plot;
            }
        }

        try {
            Statement stmt = conn.createStatement();
            results = stmt.executeQuery("SELECT * FROM " + Metro.getTableName(Metro.Tables.Plot) + " WHERE x = " + x +
                                        " AND y = " + y + ";");

            if(results.first() && results.getInt("x") == x && results.getInt("y") == y) {

            } else {
                Chunk chunk = main.getChunkAt(x,y);
            }

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * Return a plot object from the specified Bukkit location
     * @author River Marmorstein
     * @param location bukkit location object
     * @return Plot object
     */
    public Plot getPlot(Location location) {
        int x = location.getChunk().getX();
        int y = location.getChunk().getZ();

        return getPlot(x,y);
    }

    /**
     *
     * @param plot object to add to the cache
     * @return
     */
    public boolean cachePlot(Plot plot) {
        if(!(plotCache.contains(plot))) {
            plotCache.add(plot);
            return true;
        }
        return false;
    }


}
