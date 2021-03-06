package banking;

import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

//TODO
/*
 Deposit to FUND account 5000					| Expect Success
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
    public void transferMethodTestNegativeTransferAmount() throws Exception {

        transferAmount = BigDecimal.valueOf(-500);

        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);

    }

    @Test(expected = MoneyLaunderingException.class)
    public void transferMethodTestOverUpperBoundaryTestAmount() throws Exception {

        transferAmount = BigDecimal.valueOf(20001);

        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);
    }

    @Test(expected = UnrecognizedAccountTypeException.class)
    public void transferMethodTestUnrecognizedException() throws Exception {

        transferAmount = BigDecimal.valueOf(500);

        when(acc1.getAccountType()).thenReturn("UnrecognizedType");
        when(acc2.getAccountType()).thenReturn("UnrecognizedType");
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc2).getAccountType();
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);
        
        verifyNoMoreInteractions(acc1,acc2);

    }
    
    @Test(expected = InsufficientFundsException.class)
    public void transferMethodTestCREDITInsufficientFundsException() throws Exception {

        transferAmount = BigDecimal.valueOf(500);

        when(acc1.getAccountType()).thenReturn("CREDIT");
        when(acc1.getBalance()).thenReturn(BigDecimal.valueOf(-29550));
        when(acc2.getAccountType()).thenReturn("CREDIT");
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        
        verify(acc2).getAccountType();
        verify(acc1).getAccountType();
        verify(acc1).getBalance();
        
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);

        verifyNoMoreInteractions(acc1,acc2);
        
    }
    
    @Test(expected = InsufficientFundsException.class)
    public void transferMethodTestDEBITInsufficientFundsException() throws Exception {

        transferAmount = BigDecimal.valueOf(500);

        when(acc1.getAccountType()).thenReturn("DEBIT");
        when(acc1.getBalance()).thenReturn(BigDecimal.valueOf(450));
        when(acc2.getAccountType()).thenReturn("CREDIT");
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc2).getAccountType();
        verify(acc1).getAccountType();
        verify(acc1).getBalance();
        
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);

        verifyNoMoreInteractions(acc1,acc2);
    }
    
    @Test(expected = DepositLimitException.class)
    public void transferMethodTestFUNDDepositLimitException() throws Exception {

        transferAmount = BigDecimal.valueOf(5001);

        when(acc2.getAccountType()).thenReturn("FUND");
        when(acc2.getBalance()).thenReturn(BigDecimal.valueOf(1000));
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc2).getAccountType();
        
        verify(acc1, never()).withdraw(transferAmount);
        verify(acc2, never()).deposit(transferAmount);
        
        verifyNoMoreInteractions(acc1,acc2);

    }
    
    @Test
    public void transferMethodTestCREDITValidTransfer() throws Exception {

        transferAmount = BigDecimal.valueOf(5000);
        
        BigDecimal acc1InitBalance = BigDecimal.ZERO;
        BigDecimal acc2InitBalance = BigDecimal.ZERO;

        when(acc1.getAccountType()).thenReturn("CREDIT");
        when(acc1.getBalance()).thenReturn(acc1InitBalance).thenReturn(acc1InitBalance.subtract(transferAmount));
        when(acc2.getAccountType()).thenReturn("CREDIT");
        when(acc2.getBalance()).thenReturn(acc2InitBalance.add(transferAmount));
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc2).getAccountType();
        verify(acc1).getAccountType();
        verify(acc1).getBalance();
        
        verify(acc1).withdraw(transferAmount);
        verify(acc2).deposit(transferAmount);
        verifyNoMoreInteractions(acc1,acc2);
        
        assertThat(acc1.getBalance(),is(equalTo(BigDecimal.valueOf(-5000))));
        assertThat(acc2.getBalance(),is(equalTo(BigDecimal.valueOf(5000))));
        
    }
    
    @Test
    public void transferMethodTestDEBITValidTransfer() throws Exception {

        transferAmount = BigDecimal.valueOf(5000);

        when(acc1.getAccountType()).thenReturn("DEBIT");
        when(acc1.getBalance()).thenReturn(BigDecimal.valueOf(10000));
        when(acc2.getAccountType()).thenReturn("CREDIT");
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc2).getAccountType();
        verify(acc1).getAccountType();
        verify(acc1).getBalance();
        
        verify(acc1).withdraw(transferAmount);
        verify(acc2).deposit(transferAmount);
        verifyNoMoreInteractions(acc1,acc2);
    }
    
    @Test
    public void transferMethodTestCREDITFUNValidTransfer() throws Exception {

        transferAmount = BigDecimal.valueOf(5000);

        when(acc1.getAccountType()).thenReturn("CREDIT");
        when(acc1.getBalance()).thenReturn(BigDecimal.valueOf(10000));
        when(acc2.getAccountType()).thenReturn("FUND");
        
        //execute
        bank.transfer(acc1, acc2, transferAmount);
        
        //Verify call order
        verify(acc2).getAccountType();
        verify(acc1).getAccountType();
        verify(acc1).getBalance();
        
        verify(acc1).withdraw(transferAmount);
        verify(acc2).deposit(transferAmount);
        verifyNoMoreInteractions(acc1,acc2);
    }
}