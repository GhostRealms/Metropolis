package net.ghostrealms;

import net.ghostrealms.plot.Plot;
import net.ghostrealms.resident.OfflineResident;
import net.ghostrealms.resident.Resident;
import net.ghostrealms.town.Town;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by River on 1/11/2015.
 * Class to handle Plot, Resident, and Town Registrations
 */
public class Registrar {

    private Metro instance;
    private Connection connection;

    private Map<UUID, Resident> onlineResidents = new HashMap<UUID, Resident>();

    public Registrar(Metro _instance) {
        this.instance = _instance;
        connection = instance.getConnection();
    }

    public Town registerTown(String name) {
        return null;
    }

    public Resident registerResident() {
        return null;
    }

    public Plot registerPlot() {
        return null;
    }

    /**
     * Update the Town in the Database
     * @param t
     */
    public void update(Town t) {

    }

    /**
     * Update the resident in the database
     * @param r
     */
    public void update(Resident r) {

    }

    /**
     * Update the plot in the database
     * @param p
     */
    public void update(Plot p) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void processLogin(UUID id) {
        try {
            PreparedStatement select = connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Resident getResident(UUID uuid) {
        return onlineResidents.get(uuid);
    }

    public OfflineResident getOfflineResident(UUID uuid) {
        return null;
    }
}
