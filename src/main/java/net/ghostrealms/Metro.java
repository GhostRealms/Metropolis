package net.ghostrealms;

import com.thoughtworks.xstream.XStream;

import net.sf.ehcache.CacheManager;

import org.bukkit.plugin.java.JavaPlugin;


public class Metro extends JavaPlugin {
    
    private static CacheManager cacheManager = new CacheManager();
    private static XStream xstream = new XStream();
    private static RealmsEconomy econ = null;
    private static Register register = null;
    private static JavaPlugin plugin = null;
    
    private Database db = null;
    
    @Override
    public void onLoad() {
        plugin = this;
        db = new Database("metro", this);
    }

    @Override
    public void onEnable() {
        
        db.execute("CREATE TABLE IF NOT EXISTS residents (" 
                   + "id VARCHAR(36) NOT NULL PRIMARY KEY," 
                   + "name VARCHAR(16) NOT NULL," 
                   + "last_seen TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()," 
                   + "joined TIMESTAMP NOT NULL," 
                   + "town INT(3)," 
                   + "friends TEXT);");
        db.execute("CREATE TABLE IF NOT EXISTS plots ("
                   + "id INT(16) NOT NULL AUTO_INCREMENT PRIMARY KEY," 
                   + "x INT(4) NOT NULL," 
                   + "y INT(4) NOT NULL," 
                   + "owner VARCHAR(36) NOT NULL," 
                   + "townOwned BOOL NOT NULL DEFAULT 0," 
                   + "town INT(3) NOT NULL default '-1'," 
                   + "users TEXT," 
                   + "flags TEXT"
                   + ");");
        db.execute("CREATE TABLE IF NOT EXISTS requests (" 
                   + "id VARCHAR(36) NOT NULL PRIMARY KEY," 
                   + "type INT(2) NOT NULL," 
                   + "data TEXT NOT NULL," 
                   + "time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()"
                   + ");");
        db.execute("CREATE TABLE IF NOT EXISTS town_map (" 
                   + "id INT(3) NOT NULL PRIMARY KEY," 
                   + "name VARCHAR(20) NOT NULL"
                   + ");");
        
        econ = new RealmsEconomy(this);
        register = new Register(this, db);
    }

    @Override
    public void onDisable() {
      db.close();
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

    public static XStream xstream() {
      return xstream;
    }

}
