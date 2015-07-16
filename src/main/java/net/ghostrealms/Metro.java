package net.ghostrealms;

import net.sf.ehcache.CacheManager;

import org.bukkit.plugin.java.JavaPlugin;


public class Metro extends JavaPlugin {
    
    private static CacheManager cacheManager = new CacheManager();
    private static RealmsEconomy econ = null;
    private static Register register = null;
    private static JavaPlugin plugin = null;
    
    private Database db = null;
    
    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        econ = new RealmsEconomy(this);
        db = new Database("metro", this);
        register = new Register(this, db);
    }

    @Override
    public void onDisable() {
        
    }
    
    public static RealmsEconomy getEconomy() {
        return econ;
    }
    
    public static Register getRegister() {
        return register;
    }
    
    public static CacheManager getCacheManager() {
        return cacheManager;
    }
    
    public static JavaPlugin plugin() {
        return plugin;
    }


}
