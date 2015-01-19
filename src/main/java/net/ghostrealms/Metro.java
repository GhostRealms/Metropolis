package net.ghostrealms;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by River on 1/11/2015.
 * Metropolis Plugin Main
 */
public class Metro extends JavaPlugin {

    private static Economy econ = null;
    private Connection connection = null;

    @Override
    public void onEnable() {
        if(!setupVault() || getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().severe("Could not instance vault");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:" + this.getDataFolder() + "metro");
        } catch (ClassNotFoundException e) {
            getLogger().severe("unable to instance h2");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        } catch (SQLException e) {
            getLogger().severe("Unable to connect to the database");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            //this should not happen.
        }
    }

    public boolean setupVault() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public static Economy getEconomy() {
        return econ;
    }


}
