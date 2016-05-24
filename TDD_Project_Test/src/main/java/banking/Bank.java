package banking;

import java.math.BigDecimal;

/**
 *
 * @author Robert Elving
 */
public class Bank implements BankIF {

    @Override
    public void transfer(AccountIF sender, AccountIF receiver, BigDecimal amount) throws NegativeTransferAmountException {

        if (amount.compareTo(BigDecimal.ZERO) == -1) {
            throw new NegativeTransferAmountException("negative transfer amount");
        }

    }

}
