package net.ghostrealms.town;

/**
 * Created by River on 1/15/2015.
 */
public abstract class TownStaffRank {

    private boolean Edit;
    private boolean Access;
    private boolean Use;

    private boolean deposit;
    private boolean withdraw;

    private boolean promote;
    private boolean demote;
    private boolean permissions;

    private boolean claim;
    private boolean unclaim;
    private boolean setforsale;

    private boolean kick;
    private boolean kick_exempt;
    private boolean ban;
    private boolean ban_exempt;

    public TownStaffRank() {
        //no args constructor for GSON
    }

}
