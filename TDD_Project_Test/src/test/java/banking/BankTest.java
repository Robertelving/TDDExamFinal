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
