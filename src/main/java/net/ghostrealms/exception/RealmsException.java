package net.ghostrealms.exception;

/**
 * Created by River on 2/1/2015.
 * just a super class for Realms based exceptions*
 */
public abstract class RealmsException extends Throwable {
    
    protected RealmsException() { super(); }
    
    protected RealmsException(String s) {
        super(s);
    }

}
