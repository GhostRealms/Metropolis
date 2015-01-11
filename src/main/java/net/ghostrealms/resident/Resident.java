package net.ghostrealms.resident;

import net.ghostrealms.FriendException;
import net.ghostrealms.TownException;
import net.ghostrealms.plot.Plot;
import net.ghostrealms.town.Town;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by River on 1/11/2015.
 */
public class Resident {
    private UUID id;
    private String username;
    private int townId;
    private List<UUID> friends = new ArrayList<UUID>();
    private List<Plot> ownedPlots = new ArrayList<Plot>();

    public void joinTown(int id) throws TownException {
        Town t = Town.getByID(id);
        if (t.isJoinable()) {
            if(!(townId == 0)) {
                leaveTown();
                townId = id;
            } else {
                townId = id;
            }
        } else {
            throw new TownException("Town not joinable");
        }
    }

    public void leaveTown() {
        this.townId = 0;
        //Set their town to 0 - null
    }

    public void addFriend(UUID id) throws FriendException {
        if(friends.contains(id)) {
            throw new FriendException("User is already a friend");
        }

        friends.add(id);
    }

    public void removeFriend(UUID id) throws FriendException {
        if(!friends.contains(id)) {
          throw new FriendException("User is not a friend");
        }
        friends.remove(id);
    }

}
