package net.ghostrealms.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {
  
  public void run(CommandSender sender, Command basecmd, String label, String[] args);

}
