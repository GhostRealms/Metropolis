package net.ghostrealms.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by River on 07-Dec-14.
 */
public class ChatUtils {

    enum Presets {}

    public static void announce(String message) {
        Bukkit.broadcastMessage(ChatColor.GRAY + "[Realms] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendPlayerChatMessage(Player p, String message) {
        p.sendMessage(ChatColor.GRAY + "[Realms] " + ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendPlayerPresetMessage(Player p, Presets preset) {

    }

    public static void sendPlayerPresetMessage(Player p, Presets preset, String... args) {

    }

}
