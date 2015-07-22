package net.ghostrealms.town;

import net.ghostrealms.resident.Resident;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Town {
  
  @Getter
  private final int tID;
  
  @Getter
  @Setter
  private Resident mayor = null;
  
  @Getter
  @Setter(AccessLevel.PRIVATE)
  private List<Resident> townStaff = new ArrayList<Resident>();
  
  @Getter
  @Setter(AccessLevel.PRIVATE)
  private List<UUID> banned = new ArrayList<UUID>();

  @Getter
  @Setter
  private List<Resident> residents = new ArrayList<Resident>();
  
  @Setter
  private boolean joinable = true;
  
  @Getter
  @Setter
  private float plotTax = -1;
  
  @Getter
  @Setter
  private float resTax = -1;
  
  @Getter
  @Setter
  private String board = "A New Town! Set your board with /t set board.";
  
  @Getter
  @Setter
  private Government govType = Government.DEMOCRACY;

  /**
   * Initialize a Town Object* 
   * @param name
   */
  public Town(String name) {
    tID = -1;
  }

  /**
   * Initialize a Town object* 
   * @param id
   */
  public Town(int id) {
    tID = id;
  }

  /**
   * Return true if the resident provided is a town staff member* 
   * @param res
   * @return true if staff
   */
  public boolean isStaff(Resident res) {
    return townStaff.contains(res);
  }

  /**
   * Add a Resident to the Town; This does not require any form of confirmation and should only
   * be used in a programmatic manner* 
   * @param resident
   */
  public void addResident(Resident resident) {
     this.residents.add(resident);
  }

  /**
   * Add a Resident as a staff member of a town*
   * @param resident
   */
  public void addStaff(Resident resident) {
    this.townStaff.add(resident);
  }

  /**
   * Return tru if the resident is on the banned member list*
   * @param res
   * @return true if banned
   */
   public boolean isBanned(Resident res){return banned.contains(res.getUUID());}

    /**
     * Bans player from town if not staff*
     * @param res
     * @return true if player is staff
     */
    public boolean addBanned(Resident res){
        if(!isBanned(res) && !isStaff(res)){
            banned.add(res.getUUID());
            return false;
        }
        return false;
    }

}
