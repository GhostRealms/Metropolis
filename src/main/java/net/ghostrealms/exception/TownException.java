package net.ghostrealms.exception;

/**
 * Created by River on 1/11/2015.
 */
public class TownException extends Exception implements RealmsException {

    public TownException() {
        super();
    }

    public TownException(String string) {
        super(string);
    }
}
