package net.ghostrealms;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Metro extends JavaPlugin {

    private static RealmsEconomy econ = null;

    @Override
    public void onEnable() {
        econ = new RealmsEconomy(this);
    }

    @Override
    public void onDisable() {
        
    }
    
    public static RealmsEconomy getEconomy() {
        return econ;
    }


}
