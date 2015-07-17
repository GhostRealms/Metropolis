package net.ghostrealms;

import net.ghostrealms.plot.Plot;
import net.ghostrealms.resident.Resident;
import net.ghostrealms.town.Town;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Register {

  private static Register me;

  
  private final JavaPlugin plugin;
  private final Database database;

  static String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }
  
  public Register(JavaPlugin plugin, Database database) {
    this.plugin = plugin;
    this.database = database;
    me = this;
  }
  
  public Plot registerPlot(Player p) {
    return null;
  }
  
  public static Register getInstance() {
    return me;
  }
  
  public void cachePlot(Plot plot) {
    //TODO add a plot to the cache
  }
  
  public void cacheResident(Resident r) {
    //TODO add a resident to the cache
  }
  
  public void cacheTown(Town t) {
    //TODO add a town to the cache
  }
  
  public Plot getPlot(int x, int z) {
    return null;
  }
  
  public Plot getPlot(Chunk c) {
    return null;
  }
  
  public Town getTown(String name) {
    ResultSet results = database.executeQuery("SELECT * FROM town_map WHERE name = " + name + ";");
    try {
      if(!results.next()) {
        return null;
      } else {
        results.beforeFirst();
        results.next();
        int id = results.getInt("id");
        return getTown(id);
      }
    } catch (SQLException ex) {
      return null; 
    }
  }
  
  public Town getTown(int tID) {
    File file = new File(getTownFolderPath() + tID + ".xml");
    if(file.exists()) {
      try {
        String xml = readFile(file.getPath(), Charset.defaultCharset());
        return (Town) Metro.xstream().fromXML(xml);
      } catch (IOException ex) {
        ex.printStackTrace();
        return null;
      }
    } else {
      return null;
    }
  }

  public Resident getResident(Player p) {
    return null;
  }
  
  public Resident getResident(UUID id) {
    return null;
  }
  
  public String getTownFolderPath() {
    String data_folder = Metro.plugin().getDataFolder().getPath();
    return data_folder + File.separator + "towns" + File.separator;
  }
  
}
