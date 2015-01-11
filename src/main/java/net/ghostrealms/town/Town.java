package net.ghostrealms.town;

import java.util.UUID;

/**
 * Created by River on 1/11/2015.
 */
public class Town {
    private int id;
    private String name;
    private UUID mayor;
    private boolean joinable;

    public static Town getByID(int id) {
        return null;
    }

    public boolean isJoinable() {
        return joinable;
    }
}
