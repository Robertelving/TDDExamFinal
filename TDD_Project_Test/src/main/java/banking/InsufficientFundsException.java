package banking;

/**
 *
 * @author Robert Elving
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}