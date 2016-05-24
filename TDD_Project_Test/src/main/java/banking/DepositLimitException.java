package banking;

/**
 *
 * @author Robert Elving
 */
public class DepositLimitException extends Exception {
    public DepositLimitException(String message) {
        super(message);
    }
}
