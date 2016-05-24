package banking;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


/**
 *
 * @author Skroget
 */
public class BankTest {


    @Before
    public void setUp() {        

    }

    @Test(expected=NegativeTransferAmountException.class)
    public void transferMethodTestNegtiveTransferAmount() throws Exception {

        BankIF bank = null;
        AccountIF acc1 = null, acc2 = null;
    
        //Actors
        bank = new Bank();
        acc1 = mock(AccountIF.class);
        acc2 = mock(AccountIF.class); 
        
        final BigDecimal transferAmount = BigDecimal.valueOf(-500);
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        verify(acc1,never()).withdraw(transferAmount);
        verify(acc2,never()).deposit(transferAmount);
        
    }
    
}
