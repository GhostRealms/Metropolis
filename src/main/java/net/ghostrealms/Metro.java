package net.ghostrealms;

import com.thoughtworks.xstream.XStream;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.AccessLevel;
import lombok.Getter;


public class Metro extends JavaPlugin {
  
    @Getter
    private static XStream xstream = new XStream();
    @Getter 
    private static RealmsEconomy econ = null;
    @Getter
    private static Register register = null;
    @Getter
    private static JavaPlugin plugin = null;
    @Getter(AccessLevel.PACKAGE)
    private static Database db = null;
    
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
                   + "type INT NOT NULL,"
                   + "data TEXT NOT NULL," 
                   + "time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()"
                   + ");");
      db.execute("CREATE TABLE IF NOT EXISTS town_map (" 
                   + "id INT(3) NOT NULL PRIMARY KEY," 
                   + "name VARCHAR(20) NOT NULL"
                   + ");");
        
      econ = new RealmsEconomy(this);
      register = new Register(this, db);
      
      register.loadTowns();
    }

    @Override
    public void onDisable() {
      db.flush();
      register.saveTowns();
    }
    
    public static JavaPlugin plugin() {
        return plugin;
    }

    public static XStream xstream() {
      return xstream;
    }
  
    public static Database database() {
      return db;
    }

}
