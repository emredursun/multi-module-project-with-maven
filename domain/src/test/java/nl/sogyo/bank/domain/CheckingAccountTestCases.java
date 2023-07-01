package nl.sogyo.bank.domain;

import nl.sogyo.bank.domain.account.CheckingAccount;
import nl.sogyo.bank.domain.account.IBankAccount;
import nl.sogyo.bank.domain.client.Client;
import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.InitializationParameterException;
import nl.sogyo.bank.domain.exception.NotAValidTransferAccountException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CheckingAccountTestCases {

    // Test equality
    @Test
    public void testCreateNewClient() throws InitializationParameterException {
        Client client = new Client("Emre Dursun");
        CheckingAccount clientAccount1 = new CheckingAccount("25", client);
        CheckingAccount clientAccount2 = new CheckingAccount("25", client);
        assertEquals(clientAccount1, clientAccount2);
    }

    // Test hashcode
    @Test
    public void testAccountsHasCodesAreNotEqual() throws InitializationParameterException {
        Client client = new Client("Emre Dursun");
        CheckingAccount clientAccount1 = new CheckingAccount("25", client);
        CheckingAccount clientAccount2 = new CheckingAccount("25", client);

        Set<CheckingAccount> accountSet = new HashSet<>();
        accountSet.add(clientAccount1);
        accountSet.add(clientAccount2);

        assertNotEquals(clientAccount1.hashCode(), clientAccount2.hashCode());
    }

    @Test
    public void negativeBalanceCheck() throws InitializationParameterException, NotAValidTransferAccountException {
        IClient customerClient = new Client("CustomerName", "CustomerAddress", "CustomerZipCode", "CustomerCity");
        IBankAccount customerCheckingAccount = new CheckingAccount("1", customerClient);

        IClient bankClient = new Client("BankName", "BankAddress", "BankZipCode", "BankCity");
        IBankAccount bankCheckingAccount = new CheckingAccount("2", bankClient);

        customerCheckingAccount.deposit(100);
        customerCheckingAccount.moneyTransfer(-10, bankCheckingAccount);
    }



}
