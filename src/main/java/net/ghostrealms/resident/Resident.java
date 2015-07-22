package net.ghostrealms.resident;

import net.ghostrealms.town.Town;

import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Resident {
  
  @Getter
  private final UUID id;
  
  @Getter
  @Setter
  private int town;
  
  @Getter
  @Setter
  private Timestamp lastseen, joined;
  
  @Getter
  @Setter(AccessLevel.PROTECTED)
  private List<UUID> friends;
  
  public Resident(Player p) {
    id = p.getUniqueId();
  }
  
  public Resident(UUID id) {
    this.id = id;
  }
  
  public void requestTown(Town t) {
  }

  public UUID getUUID(){ return id; }

}
