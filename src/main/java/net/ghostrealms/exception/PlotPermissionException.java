package net.ghostrealms.exception;

/**
 * Created by River on 30-Nov-14.
 * Thrown when there are insufficient permissions to preform an action on a plot
 */
public class PlotPermissionException extends Exception {

    public PlotPermissionException() {}

    public PlotPermissionException(String message) {
        super(message);
    }
}
