package net.ghostrealms.resident;

import net.ghostrealms.exception.FriendException;
import net.ghostrealms.Metro;
import net.ghostrealms.exception.InsufficientFundsException;
import net.ghostrealms.exception.TownException;
import net.ghostrealms.plot.Plot;
import net.ghostrealms.town.Town;
import org.bukkit.Bukkit;

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
    
    public Resident(UUID id) {
        // pew pew todo
    }

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

    public UUID getUUID() {
        return id;
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
    
    public List<Resident> getFriends() {
        List<Resident> list = new ArrayList<Resident>();
        for(UUID id : friends) {
            list.add(new Resident(id));
        }
        return list;
    }

    public double getBalance() {
        return Metro.getEconomy().getBalance(Bukkit.getOfflinePlayer(id));
    }

    public void withdraw(double amount) {
        Metro.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(id), amount);
    }

    public void deposit(double amount) {
        Metro.getEconomy().depositPlayer(Bukkit.getOfflinePlayer(id), amount);
    }

    public void pay(Resident res, double amount) throws InsufficientFundsException {
        double balance = Metro.getEconomy().getBalance(Bukkit.getOfflinePlayer(id));
        if(balance > amount) {
            Metro.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(id), amount);
            Metro.getEconomy().depositPlayer(Bukkit.getOfflinePlayer(res.getUUID()), amount);
        } else {
            throw new InsufficientFundsException();
        }
    }

}
