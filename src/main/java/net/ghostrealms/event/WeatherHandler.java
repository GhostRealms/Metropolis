package net.ghostrealms.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;


public class WeatherHandler implements Listener {

  @EventHandler
  public void onWeather(WeatherChangeEvent event) {
    event.setCancelled(true);
    //so I can afk without the damn weather bothering me
  }
  
}
