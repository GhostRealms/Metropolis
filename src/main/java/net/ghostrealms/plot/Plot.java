package net.ghostrealms.plot;

import net.ghostrealms.Metro;
import net.ghostrealms.Register;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Plot {
  
  private final Register register = Register.getInstance();
  
  private final int x;
  private final int z;
  private final Chunk chunk;
  
  private UUID owner;
  private List<UUID> permitted = new ArrayList<UUID>();
  
  public Plot(int x, int z) {
    this.x = x;
    this.z = z;
    this.chunk = Bukkit.getWorld(Metro.plugin().getConfig().getString("world")).getChunkAt(x, z);
  }
  
  public Plot(Player player) {
    Chunk c = player.getLocation().getChunk();
    this.chunk = c;
    x = c.getX();
    z = c.getZ();
  }
  
  public Plot(Location location) {
    this.chunk = location.getChunk();
    x = chunk.getX();
    z = chunk.getZ();
  }
  public void addPermitted(UUID newPermitted){ this.permitted.add(newPermitted); }

  public void setOwner(UUID newOwner) {
    this.owner = newOwner;
  }


  private void serialize() {
  }
  
  private void deserialize() {
  }
  
}
