package banking;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

//TODO
/*
    Withdraw less than 20000 on any account with sufficient funds 	| Expect Success
    Withdraw more that 20000 on any account 			  	| Expect Exception
    Withdraw from CREDIT account so final balance > -30000 		| Expect Success
    Withdraw from CREDIT account so final balance < -30000		| Expect Exception
    Withdraw from DEBIT account so final balance >= 0			| Expect Success
    Withdraw from DEBIT account so final balance < -			| Expect Exception
    Deposit to FUND account 5000					| Expect Success
    Deposit to FUND account 5001					| Expect Exception
    
*/
public class BankTest {

    private BankIF bank = null;
    private AccountIF acc1 = null, acc2 = null;
    private BigDecimal transferAmount;
    
    @Before
    public void setUp() {
        //Actors
        bank = new Bank();
        acc1 = mock(AccountIF.class);
        acc2 = mock(AccountIF.class);
    }

    @Test(expected = NegativeTransferAmountException.class)
    public void transferMethodTestNegtiveTransferAmount() throws Exception {

        transferAmount = BigDecimal.valueOf(-500);

        //execute
        bank.transfer(acc1, acc2, transferAmount);
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);

    }

}
