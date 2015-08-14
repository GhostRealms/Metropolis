package net.ghostrealms.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
  
  private final Command base;
  
  private Map<String, SubCommand> subCommandMap;
  private Map<String, String> commandPermissionMap;

  public CommandManager(Command baseCmd) {
    base = baseCmd;
    subCommandMap = new HashMap<String, SubCommand>();
    commandPermissionMap = new HashMap<String, String>();
  }

  /**
   * Call a subcommand
   * @param subCommand
   * @param sender
   * @param args
   */
  public void call(String subCommand, CommandSender sender, String[] args) {
    SubCommand exe = subCommandMap.get(subCommand);
    if(exe == null) {
      return;
    }
    
    //Testing: Check if the player has the permission defined by the map before processing the
    //command
    if(commandPermissionMap.containsKey(subCommand) && commandPermissionMap.get(subCommand) != null) {
      if(!(sender.hasPermission(commandPermissionMap.get(subCommand)))) {
	return;
      }
    }
    
    exe.run(sender, base, subCommand, args);
  }
  
  public boolean register(String sub, SubCommand exe) {
    if(subCommandMap.containsKey(sub)) {
      return false;
    } else {
      subCommandMap.put(sub, exe);
      return true;
    }
  }
  
  public boolean unRegister(String sub) {
    subCommandMap.remove(sub);
    return true;
  }
  
  public void setPermission(String sub, String perm) {
    if(commandPermissionMap.containsKey(sub)) {
      return;
    }
    
    commandPermissionMap.put(sub, perm);
  }

}
