package net.ghostrealms.metropolis;

import net.ghostrealms.protection.Plot;
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


}
