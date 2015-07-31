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
public final class CreateTownEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private Town town;

    @Getter
    private UUID resident;

    public CreateTownEvent(Town town, UUID resident) {
        this.town = town;
        this.resident = resident;
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
     * The founder of the town's id
     * @return resident
     */
    public UUID getFounderId() {
        return resident;
    }

    /**
     * The founder as a player
     * @return resident
     */
    public Player getFounder(){
        return Bukkit.getPlayer(getFounderId());
    }
}
