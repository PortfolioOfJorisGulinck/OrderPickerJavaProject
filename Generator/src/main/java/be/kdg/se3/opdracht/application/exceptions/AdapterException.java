package be.kdg.se3.opdracht.application.exceptions;

/**
 * Wrapper for anything that goes wrong during communication with an external system through an adapter
 * This can include both network failures and formatOrder conversion errors (!)
 */
public class AdapterException extends Exception {
    public AdapterException(String message, Exception cause) {
        super(message, cause);
    }

    public AdapterException(String message) {
        super(message);
    }
}
