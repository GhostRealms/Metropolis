package net.ghostrealms;

import net.ghostrealms.plot.Plot;
import net.ghostrealms.resident.Resident;
import net.ghostrealms.town.Town;
import net.sf.ehcache.Cache;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class Register {

  private static Register me;

  private static Cache plotCache = new Cache("plot", 200, false, false, 24 * 60 * 60, 24 * 60 * 60);
  
  private final JavaPlugin plugin;
  private final Database database;
  
  public Register(JavaPlugin plugin, Database database) {
    this.plugin = plugin;
    this.database = database;
    Metro.getCacheManager().addCache(plotCache);
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
  
  public Plot getPlot(int x, int z) {
    return null;
  }
  
  public Plot getPlot(Chunk c) {
    return null;
  }
  
  public Town getTown(String name) {
    return null;
  }
  
  public Town getTown(int tID) {
    return null;
  }
  
  public Resident getResident(Player p) {
    return null;
  }
  
  public Resident getResident(UUID id) {
    return null;
  }
  
}
