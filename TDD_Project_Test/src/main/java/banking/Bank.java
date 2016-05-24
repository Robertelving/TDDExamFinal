package banking;

import java.math.BigDecimal;

/**
 *
 * @author Robert Elving
 */
public class Bank implements BankIF {

    @Override
    public void transfer(AccountIF sender, AccountIF receiver, BigDecimal amount) throws NegativeTransferAmountException, MoneyLaunderingException {
        
        if (amount.compareTo(BigDecimal.ZERO) == -1) {
            throw new NegativeTransferAmountException("negative transfer amount");
        }else if(amount.compareTo(BigDecimal.valueOf(20000)) == 1){
            throw new MoneyLaunderingException("Drug Money Detected");
        } 

    }

}
