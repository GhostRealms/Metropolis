package net.ghostrealms;

import net.ghostrealms.plot.Plot;
import net.ghostrealms.resident.Resident;
import net.ghostrealms.town.Town;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Register {

  private static Register me;

  
  private final JavaPlugin plugin;
  private final Database database;
  
  private Map<Integer, Town> loadedTowns = new HashMap<Integer, Town>();
  private Map<UUID, Resident> onlineResidents = new HashMap<UUID, Resident>();

  static String readFile(String path) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, Charset.defaultCharset());
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
  
  protected void loadTowns() {
    for(File f : new File(plugin.getDataFolder() + File.separator + "towns").listFiles()) {
      String name = f.getName();
      String[] split = name.split(".");
      int id = Integer.parseInt(split[0]);
      try {
        String xml = readFile(f.getPath());
        loadedTowns.put(id, (Town) Metro.xstream().fromXML(xml));
      } catch (IOException e) {
        e.printStackTrace();
        return;
      }
    }
  }
  
  protected void saveTowns() {
    for (Town t : loadedTowns.values()) {
      FileOutputStream out = null;
      try {
        String xml = Metro.xstream().toXML(t);
        out = new FileOutputStream(getTownFile(t));
        out.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
        byte[] bytes = xml.getBytes("UTF-8");
        out.write(bytes);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      } catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
      } catch (IOException ex) {
        ex.printStackTrace();
      } finally {
        if(out != null) {
          try {
            out.close();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      }
    }
  }
  
  public Town getTown(int tID) {
    if(loadedTowns.containsKey(tID)) {
      return loadedTowns.get(tID);
    }
    
    File file = new File(getTownFolderPath() + tID + ".xml");
    if(file.exists()) {
      try {
        String xml = readFile(file.getPath());
        return (Town) Metro.xstream().fromXML(xml);
      } catch (IOException ex) {
        ex.printStackTrace();
        return null;
      }
    } else {
      return null;
    }
  }
  
  public File getTownFile(Town town) {
    File file = new File(getTownFolderPath() + File.separator + town.getTID() + ".xml");
    if(file.exists()) {
      return file;
    } else {
      try {
        file.createNewFile();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      return file;
    }
  }

  public Resident getResident(Player p) {
    if(p.isOnline()) {
      if(onlineResidents.containsKey(p.getUniqueId())) {
        return onlineResidents.get(p.getUniqueId());
      } else {
        return new Resident(p);
      }
    } else {
      return new Resident(p);
    }
  }
  
  public Resident getResident(UUID id) {
    if(onlineResidents.containsKey(id)) {
      return onlineResidents.get(id);
    } else {
      return new Resident(id);
    }
  }

  /**
   * Creates a resident object and adds that object to the online resident mapping cache 
   * @param player
   */
  protected void loginResident(Player player) {
    Resident res = new Resident(player);
    onlineResidents.put(player.getUniqueId(), res);
  }

  /**
   * Remove a resident from the logged in listing, called on the PlayerQuit and PlayerLogout events 
   * @param uuid
   */
  protected void logoutResident(UUID uuid) {
    if(onlineResidents.containsKey(uuid)) {
      onlineResidents.remove(uuid);
    }
  }
  
  public String getTownFolderPath() {
    String data_folder = Metro.plugin().getDataFolder().getPath();
    return data_folder + File.separator + "towns" + File.separator;
  }
  
}
