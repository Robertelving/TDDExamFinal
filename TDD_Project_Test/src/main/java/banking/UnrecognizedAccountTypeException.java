package banking;

/**
 *
 * @author Robert Elving
 */
public class UnrecognizedAccountTypeException extends Exception {
    public UnrecognizedAccountTypeException(String message) {
        super(message);
    }
}
