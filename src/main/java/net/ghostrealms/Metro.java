package net.ghostrealms;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
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

    private boolean startupRan;

    private static Connection connection = null;
    private BukkitScheduler scheduler;
    private File dataFolder;
    private File townFolder;

    private static LangLib language;
    private static Metro instance;
    private static Economy econ = null;
    private static Chat chat = null;

    public enum Tables {Resident,Town,State,Plot,Users,Stats}

    @Override
    public void onLoad() {
        try {
            runStartup();
            startupRan = true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            startupRan = false;
        }

        dataFolder = this.getDataFolder();
    }

    @Override
    public void onEnable() {

        if(!startupRan) {
            getServer().getPluginManager().disablePlugin(this);
        }

        this.saveDefaultConfig();
        scheduler = getServer().getScheduler();

        language = new LangLib(this);
        instance = this;
        setupVault();


    }

    @Override
    public void onDisable() {
        language = null;
        instance = null;
        scheduler = null;
        try {
            connection.close();
            connection = null;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void runStartup() throws SQLException {

    }

    private void setupVault() {
        RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(Economy.class);
        econ = econProvider.getProvider();

        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
        chat = chatProvider.getProvider();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static LangLib getLanguage() {
        return language;
    }

    public static Metro getInstance() {
        return instance;
    }

    public static Economy getEconomy() { return econ; }

    public static Chat getChat() {return chat;}
}
