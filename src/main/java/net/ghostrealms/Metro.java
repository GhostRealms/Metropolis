package net.ghostrealms;

import net.ghostrealms.metropolis.eco.Taxes;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by River on 28-Nov-14 23:10.
 * Main Metropolis Plugin Class
 */

public class Metro extends JavaPlugin {

    private static Connection connection = null;
    private BukkitScheduler scheduler;

    enum Tables {Resident,Town,State,Plot,Users,Stats}

    private static Map<Tables, String> tableNames = new HashMap<Tables, String>();

    @Override
    public void onLoad() {
        try {
            runstartup();
        } catch (SQLException e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        tableNames.put(Tables.Resident, getConfig().getString("database.tables.resident"));
        tableNames.put(Tables.Town, getConfig().getString("database.tables.town"));
        tableNames.put(Tables.Plot, getConfig().getString("database.tables.plot"));
        tableNames.put(Tables.State, getConfig().getString("database.tables.state"));
        tableNames.put(Tables.Users, getConfig().getString("database.tables.user"));
        tableNames.put(Tables.Stats, getConfig().getString("database.tables.stats"));
        scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Taxes(this), 0L, 1728000L); //Run the tax task every 24 hours
    }

    @Override
    public void onDisable() {

    }

    public void connectDatabase() {

    }

    public static String getTableName(Tables table) {
        return tableNames.get(table);
    }

    private void runstartup() throws SQLException {
        String url = "jdbc:mysql://" + getConfig().getString("database.host") + ":" +
                getConfig().getInt("database.port") + "/" + getConfig().getString("database.name");
        connection = DriverManager.getConnection(url, getConfig().getString("database.user"),
                getConfig().getString("database.pass"));
        if(connection == null) {
            throw new SQLException("Unable to Load the Database");
        }

        Statement statement = connection.createStatement();

        statement.execute("CREATE TABLE IF NOT EXISTS plots (" +
                "id int(3) NOT NULL AUTO_INCREMENT," +
                "x INT NOT NULL," +
                "y INT NOT NULL," +
                "world varchar(30) NOT NULL," +
                "owner varchar(255)," +
                "use varchar(255)," +
                "edit varchar(255)," +
                "access varchar(255)," +
                "banned varchar(255)," +
                "flags varchar(255)," +
                "PRIMARY KEY (id) " +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS residents (" +
                "uuid varchar(35) NOT NULL," +
                "town INT," +
                "town_role varchar(20)," +
                "join_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (uuid)" +
                ");");

        statement.execute("CREATE TABLE IF NOT EXISTS towns (" +
                "id INTEGER NOT NULL AUTO_INCREMENT," +
                "name varchar(10) NOT NULL," +
                "bank float NOT NULL," +
                "spawnCoord varchar(50) NOT NULL," + //Stored in x,y,z,YAW,PITCH format. uses the world of the registrar
                "mayor varchar(255) NOT NULL," +
                "create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "primary key (id)"+
                "); ");
    }

    public static Connection getConnection() {
        return connection;
    }
}
