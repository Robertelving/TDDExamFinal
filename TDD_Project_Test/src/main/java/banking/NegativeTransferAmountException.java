package banking;

/**
 *
 * @author Robert Elving
 */
public class NegativeTransferAmountException extends Exception {
    public NegativeTransferAmountException(String message) {
        super(message);
    }
}
