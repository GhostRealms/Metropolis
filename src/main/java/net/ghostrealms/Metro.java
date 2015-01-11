package net.ghostrealms;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by River on 1/11/2015.
 * Metropolis Plugin Main
 */
public class Metro extends JavaPlugin {

    private Economy econ = null;

    @Override
    public void onEnable() {
        if(!setupVault() || getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().severe("Could not instance vault");
            this.getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {

    }

    public boolean setupVault() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
