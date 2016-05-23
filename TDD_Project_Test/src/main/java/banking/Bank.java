package banking;

import java.math.BigDecimal;

/**
 *
 * @author Robert Elving
 */
public class Bank implements BankIF{

    @Override
    public boolean transfer(AccountIF sender, AccountIF receiver, BigDecimal amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
