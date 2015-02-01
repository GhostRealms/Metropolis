package net.ghostrealms;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.ghostrealms.exception.InvalidDependencyException;
import net.ghostrealms.exception.RealmsException;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.omg.CORBA.DynAnyPackage.Invalid;

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
    
    private WEapi worldedit;
    private WGapi worldguard;

    @Override
    public void onEnable() {
        if(!setupVault() || getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().severe("Could not instance vault");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        try {
            setupWorldGuard();
        } catch (InvalidDependencyException ex) {
            ex.printStackTrace();
            this.getServer().getPluginManager().disablePlugin(this);
        } catch (RealmsException ex) {
            ex.printStackTrace();
            //this should not happen - it should be caught in the Invalid Dependency exception
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
    
    public void setupWorldGuard() throws RealmsException {
        WorldGuardPlugin plugin = WGBukkit.getPlugin();
        Plugin worldEditPlugin = getServer().getPluginManager().getPlugin("WorldEdit");
        if(worldEditPlugin == null || !(worldEditPlugin instanceof WorldEditPlugin)) {
            throw new InvalidDependencyException(worldEditPlugin);
        }
        worldedit = new WEapi((WorldEditPlugin) worldEditPlugin);
        worldguard = new WGapi(plugin);
    }

    public Connection getConnection() {
        return connection;
    }

    public static Economy getEconomy() {
        return econ;
    }


}
