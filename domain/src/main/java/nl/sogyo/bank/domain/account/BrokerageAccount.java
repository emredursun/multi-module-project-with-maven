package nl.sogyo.bank.domain.account;

import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.InitializationParameterException;
import nl.sogyo.bank.domain.exception.NotAValidTransferAccountException;

public class BrokerageAccount extends BankAccount {

    /**
     * Constructor
     *
     * @param accountNumber Number of the new account.
     * @param owner         Client who owns the account.
     */
    public BrokerageAccount(String accountNumber, IClient owner) throws InitializationParameterException {
        super(accountNumber, owner);
        setType(BankAccountType.EFFECTENREKENING);
    }

    public void moneyTransfer(double amount, IBankAccount account) throws NotAValidTransferAccountException {
        // TODO implement.
    }

}