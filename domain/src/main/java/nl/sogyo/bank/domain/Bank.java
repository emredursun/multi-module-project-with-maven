package nl.sogyo.bank.domain;

import nl.sogyo.bank.domain.account.*;
import nl.sogyo.bank.domain.client.Client;
import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.AccountNotFoundException;
import nl.sogyo.bank.domain.exception.ClientNotFoundException;
import nl.sogyo.bank.domain.exception.InitializationParameterException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Bank implements IBank {

    /**
     * Starting accountnumber for checking accounts.
     */
    private final int minCheckingAccountNumber = 100000000;

    /**
     * Starting accountnumber for savings accounts.
     */
    private final int minSavingsAccountNumber = 1000000000;

    /**
     * Collection of all clients of the bank. No duplicates allowed.
     */
    private final Set<IClient> clients = new HashSet<IClient>();

    /**
     * Collection of all accounts of the bank. No duplicates allowed.
     */
    private final Set<IBankAccount> accounts = new HashSet<IBankAccount>();

    /**
     * @see IBank#createNewClient(String,
     * String, String)
     */
    public IClient createNewClient(String name, String adress, String zipCode)
            throws InitializationParameterException {

        return createNewClient(name, adress, zipCode, null);
    }

    /**
     * @see IBank#createNewClient(String,
     * String, String, String)
     */
    public IClient createNewClient(String name, String adress, String zipCode,
                                   String city) throws InitializationParameterException {

        // You also need to override the equals method in Client for this
        // to be successful.
        IClient client = new Client(name, adress, zipCode, city);
        IAccountPortfolio portfolio = new AccountPortfolio(client);
        this.clients.add(client);

        return client;
    }

    /**
     * Creates a new accountnumber.
     *
     * @param type Type of account to create the new accountnumber for.
     * @return New accountnumber.
     */
    private String createAccountNumber(char type) {

        String accountNumber = "";
        int count = this.getAllAccounts().size();
        if (IBankAccount.REKENINGCOURANT == type
                || IBankAccount.EFFECTENREKENING == type) {
            accountNumber += (this.minCheckingAccountNumber + count);
        } else if (IBankAccount.SPAARREKENING == type) {
            accountNumber += (this.minSavingsAccountNumber + count);

        }
        return accountNumber;
    }

    /**
     * @see IBank#createNewCheckingAccount(IClient)
     */
    public IBankAccount createNewCheckingAccount(IClient owner)
            throws InitializationParameterException {

        IBankAccount checkingAccount = new CheckingAccount(this
                .createAccountNumber(IBankAccount.REKENINGCOURANT), owner);
        this.accounts.add(checkingAccount);
        owner.getAccountPortfolio().addAccount(checkingAccount);

        return checkingAccount;
    }

    /**
     * @see IBank#createNewSavingsAccount(IClient)
     */
    public IBankAccount createNewSavingsAccount(IClient owner)
            throws InitializationParameterException {

        IBankAccount savingsAccount = new SavingsAccount(this
                .createAccountNumber(IBankAccount.SPAARREKENING), owner);
        this.accounts.add(savingsAccount);
        owner.getAccountPortfolio().addAccount(savingsAccount);

        return savingsAccount;
    }

    /**
     * @see IBank#createNewBrokerageAccount(IClient)
     */
    public IBankAccount createNewBrokerageAccount(IClient owner)
            throws InitializationParameterException {

        IBankAccount brokerageAccount = new BrokerageAccount(this
                .createAccountNumber(IBankAccount.EFFECTENREKENING), owner);
        this.accounts.add(brokerageAccount);
        owner.getAccountPortfolio().addAccount(brokerageAccount);

        return brokerageAccount;
    }

    /**
     * @see IBank#getAllClients()
     */
    public Collection<IClient> getAllClients() {
        return this.clients;
    }

    /**
     * @see IBank#getClient(String,
     * String, String)
     */
    public IClient getClient(String name, String adress, String zipCode)
            throws ClientNotFoundException {

        boolean found = false;

        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException(
                    "One or more of the arguments \"name\", \"adress\" or \"zipCode\" is \"null\" or empty.");
        }

        Iterator<IClient> itr = this.getAllClients().iterator();

        while (itr.hasNext()) {
            IClient client = itr.next();

            if (name.equalsIgnoreCase(client.getName())) {
                found = true;
                return client;
            }
        }

        if (!found) {
            throw new ClientNotFoundException("name = " + name + ", adress = "
                    + adress + ", zipcode = " + zipCode);
        }

        return null;
    }

    /**
     * @see IBank#getAllAccounts()
     */
    public Collection<IBankAccount> getAllAccounts() {
        return this.accounts;
    }

    /**
     * @see IBank#getBankAccount(String)
     */
    public IBankAccount getBankAccount(String accountNumber)
            throws AccountNotFoundException {

        boolean found = false;

        if (accountNumber == null || "".equals(accountNumber)) {

            throw new IllegalArgumentException(
                    "Argument \"accountNumber\" is \"null\" or empty.");
        }

        Iterator<IBankAccount> itr = this.getAllAccounts().iterator();

        while (itr.hasNext()) {
            IBankAccount account = itr.next();

            if (accountNumber.equals(account.getAccountNumber())) {
                return account;
            }
        }

        if (!found) {
            throw new AccountNotFoundException("accountnumber = " + accountNumber);
        }

        return null;
    }

}