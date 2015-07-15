package net.ghostrealms;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class RealmsEconomy {

  private Economy econ = null;
  private Chat chat = null;
  private Permission perms = null;
  
  public RealmsEconomy(JavaPlugin plugin) {
    RegisteredServiceProvider<Economy> economyRegisteredServiceProvider = 
	plugin.getServer().getServicesManager().getRegistration(Economy.class);
    econ = economyRegisteredServiceProvider.getProvider();
    RegisteredServiceProvider<Chat> chatRegisteredServiceProvider = 
	plugin.getServer().getServicesManager().getRegistration(Chat.class);
    chat = chatRegisteredServiceProvider.getProvider();
    RegisteredServiceProvider<Permission> permissionRegisteredServiceProvider =
	plugin.getServer().getServicesManager().getRegistration(Permission.class);
    perms = permissionRegisteredServiceProvider.getProvider();
  }
  
  public Economy getNativeEconomy() {
    return econ;
  }
  
  public Chat getNativeChat() {
    return chat;
  }
  
  public Permission getNativePermissions() {
    return perms;
  }
  
  public void deposit(Player p, Double amount) {
    econ.depositPlayer((OfflinePlayer) p, amount);
  }
  
  public void withdraw(Player p, Double amount) {
    econ.withdrawPlayer((OfflinePlayer) p, amount);
  }
  
  public void setBalance(Player p, Double amount) {
    double diff = amount - econ.getBalance((OfflinePlayer) p);
    if(diff > 0) {
      econ.depositPlayer((OfflinePlayer) p, diff);
    } else {
      econ.withdrawPlayer((OfflinePlayer) p, Math.abs(diff));
    }
  }

}
