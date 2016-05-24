package banking;

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;


/**
 *
 * @author Skroget
 */
public class BankTest {


    @Before
    public void setUp() {        

    }

    
    @Test
    public void transferTestCallOrder() {

        BankIF bank = null;
        AccountIF acc1 = null, acc2 = null;
    
        //Actors
        bank = new Bank();
        acc1 = mock(AccountIF.class);
        acc2 = mock(AccountIF.class); 
        
        final BigDecimal transferAmount = BigDecimal.valueOf(500);
        
        //execute
        boolean result = bank.transfer(acc1, acc2, transferAmount);
        
        verify(acc1).withdraw(transferAmount);
        verify(acc2).deposit(transferAmount);
        
        verifyNoMoreInteractions(acc1, acc2);

        assertThat(result, is(true));
        
    }
    
}
