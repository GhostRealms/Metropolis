package net.ghostrealms.event;

import lombok.Getter;
import net.ghostrealms.town.Town;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by James on 7/21/2015.
 */
public class DemoteResidentEvent {
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private Town town;

    @Getter
    private UUID toDemoteResident;

    @Getter
    private UUID DemoterResident;

    public DemoteResidentEvent(Town town, UUID toDemoteResident, UUID DemoterResident) {
        this.town = town;
        this.toDemoteResident = toDemoteResident;
        this.DemoterResident = DemoterResident;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Return town that was created
     * @return town
     */
    public Town getTown() {
        return town;
    }

    /**
     * The resident that is being Demoted
     * @return toDemoteResidentId
     */
    public UUID getToDemoteResidentId() {
        return toDemoteResident;
    }

    /**
     * The resident that is being Demoted
     * @return toDemoteResidentId
     */
    public Player getToDemoteResident(){
        return Bukkit.getPlayer(getToDemoteResidentId());
    }

    /**
     * The resident that is promoting
     * @return DemoterResidentId
     */
    public UUID getDemoterResidentId(){
        return DemoterResident;
    }

    /**
     * The resident that is being Demoted
     * @return toDemoteResidentId
     */
    public Player getDemoterResident(){
        return Bukkit.getPlayer(getDemoterResidentId());
    }
}
