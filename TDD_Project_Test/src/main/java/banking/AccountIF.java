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
    public void withdraw(BigDecimal amount);
    
    //deposits amount to account 
    public void deposit(BigDecimal amount);

}
