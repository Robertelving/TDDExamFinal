package banking;

import java.math.BigDecimal;

/**
 *
 * @author Robert Elving
 */
public class Bank implements BankIF {

    @Override
    public void transfer(AccountIF sender, AccountIF receiver, BigDecimal amount) throws NegativeTransferAmountException, MoneyLaunderingException, UnrecognizedAccountTypeException, InsufficientFundsException, DepositLimitException {
        
        if (amount.compareTo(BigDecimal.ZERO) == -1) {
            throw new NegativeTransferAmountException("negative transfer amount");
        }else if(amount.compareTo(BigDecimal.valueOf(20000)) == 1){
            throw new MoneyLaunderingException("Drug Money Detected");
        } 
         
        switch(receiver.getAccountType()){
            case "FUND":
                if(amount.compareTo(BigDecimal.valueOf(5000)) == 1){
                    throw new DepositLimitException("FUND account Deposit limit crossed");
                }
            case "CREDIT":
            case "DEBIT":
                break;
            default:
                throw new UnrecognizedAccountTypeException("Receiver Account Type not recognized");
        }
        
        switch(sender.getAccountType()){
            case "CREDIT":
                if(sender.getBalance().subtract(amount).compareTo(BigDecimal.valueOf(-30000)) == -1){
                    throw new InsufficientFundsException("CREDIT Account Insufficient funds");
                }
            break;
            case "FUND" :
            case "DEBIT":
                if(sender.getBalance().subtract(amount).compareTo(BigDecimal.valueOf(0)) == -1){
                    throw new InsufficientFundsException("DEBIT Account Insufficient funds");
                }
            break;
            default:
                throw new UnrecognizedAccountTypeException("Sender Account Type not recognized");
        }
        
        sender.withdraw(amount);
        receiver.deposit(amount);
        

    }

}
