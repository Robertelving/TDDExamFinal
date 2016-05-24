package banking;

import java.math.BigDecimal;

/**
 *
 * @author Skroget
 */
public interface AccountIF {

    //returns current balance
    public BigDecimal getBalance();
    
    //returns account type
    public String getAccountType();
    
    //withdraws amount from balance
    public BigDecimal withdraw(BigDecimal amount);
    
    //deposits amount to account 
    public BigDecimal deposit(BigDecimal amount);

}
