package net.ghostrealms.event;

import lombok.Getter;
import net.ghostrealms.town.Town;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * Created by James on 7/21/2015.
 */
public final class PromoteResidentEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private Town town;

    @Getter
    private UUID toPromoteResident;

    @Getter
    private UUID promoterResident;

    public PromoteResidentEvent(Town town, UUID toPromoteResident, UUID promoterResident) {
        this.town = town;
        this.toPromoteResident = toPromoteResident;
        this.promoterResident = promoterResident;
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
     * The resident that is being promoted
     * @return toPromoteResidentId
     */
    public UUID getToPromoteResidentId() {
        return toPromoteResident;
    }

    /**
     * The resident that is being promoted
     * @return toPromoteResidentId
     */
    public Player getToPromoteResident(){
        return Bukkit.getPlayer(getToPromoteResidentId());
    }

    /**
     * The resident that is promoting
     * @return promoterResidentId
     */
    public UUID getPromoterResidentId(){
        return promoterResident;
    }

    /**
     * The resident that is being promoted
     * @return toPromoteResidentId
     */
    public Player getPromoterResident(){
        return Bukkit.getPlayer(getPromoterResidentId());
    }

}
