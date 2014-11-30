package net.ghostrealms.exception;

/**
 * Created by River on 30-Nov-14.
 * Thrown when their is an invalid balance to complete a transaction
 */
public class EconomyException extends Exception {

    public EconomyException() {}

    public EconomyException(String message) {
        super(message);
    }
}
