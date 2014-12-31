package net.ghostrealms.commands;

import mkremins.fanciful.FancyMessage;
import net.ghostrealms.Metro;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * Created by River on 29-Dec-14.
 */

public class MetroCommandExecutor implements CommandExecutor {

    private Metro instance;
    private PluginDescriptionFile pluginDescriptionFile;

    public MetroCommandExecutor(Metro instance) {
        this.instance = instance;
        pluginDescriptionFile = instance.getDescription();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("metro:version")) {
            sendVersion(sender);
            return true;
        } else if(label.equalsIgnoreCase("metro:info")) {
            sendInfo(sender);
            return true;
        } else {
            if(args.length == 0) {
                sendVersion(sender);
                sendInfo(sender);
                return true;
            } else {
                if(args[1].equalsIgnoreCase("version") || args[1].equalsIgnoreCase("-v") || args[1].equalsIgnoreCase("v")) {
                    sendVersion(sender);
                    return true;
                } else if(args[1].equalsIgnoreCase("info") || args[1].equalsIgnoreCase("-i") || args[1].equalsIgnoreCase("i")) {
                    sendInfo(sender);
                    return true;
                } else {
                    sender.sendMessage("hi");
                    return false;
                }
            }
        }
    }

    public void sendVersion(CommandSender sender) {
        sender.sendMessage("hi");
    }

    public void sendInfo(CommandSender sender) {

    }
}
