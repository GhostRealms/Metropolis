package net.ghostrealms.exception;

/**
 * Created by River on 30-Nov-14.
 * Thrown when the plot being used is invalid (not owned, or not claimed)
 */
public class InvalidPlotException extends Exception {

    public InvalidPlotException() {}

    public InvalidPlotException(String message) {
        super(message);
    }

}
