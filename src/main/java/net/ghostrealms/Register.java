package net.ghostrealms;

import net.ghostrealms.plot.Plot;
import net.sf.ehcache.Cache;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
  
}
