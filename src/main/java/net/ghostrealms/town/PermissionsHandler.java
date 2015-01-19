package net.ghostrealms.town;

/**
 * Created by River on 1/18/2015.
 */
public class PermissionsHandler {

    private PermissionsHandler() {
        //This is a static class. We don't like new objects.
    }

    enum PermissionType {
        ACCESS_ALL,
        EDIT_ALL,
        USE_ALL,
        BANK_DEPOSIT,
        BANK_WITHDRAW,
        PROMOTE_RESIDENT,
        DEMOTE_RESIDENT,
        EDIT_PERMISSIONS,
        CLAIM_PLOT,
        UNCLAIM_PLOT,
        SETFORSALE_PLOT,
        KICK_RESIDENT,
        KICK_EXEMPT
    }

    /**
     * Checks if a Staff Rank has a PermissionType
     * @param rank
     * @param checkFor
     * @return true/false
     */
    public static boolean hasPermission(TownStaffRank rank, PermissionType checkFor) {
        return false;
        //TODO map the perm types and the boolean values in the object
    }
}
