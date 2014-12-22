package net.ghostrealms.exception;


/**
 * Created by River on 30-Nov-14.
 * Thrown when a location or world trying to be accessed does not exist
 */
public class LocationDoesNotExistException extends Exception {

    public LocationDoesNotExistException() {
    }

    public LocationDoesNotExistException(String message) {
        super(message);
    }


}
