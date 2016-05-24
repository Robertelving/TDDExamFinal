package banking;

import java.math.BigDecimal;

/**
 *
 * @author Skroget
 */
public interface BankIF {
    
    public void transfer(AccountIF sender, AccountIF receiver, BigDecimal amount) throws Exception;
    
}
