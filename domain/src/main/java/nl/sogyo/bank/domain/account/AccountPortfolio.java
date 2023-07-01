package nl.sogyo.bank.domain.account;

import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.AccountPortfolioExcpetion;

import java.util.Collection;
import java.util.HashMap;

public class AccountPortfolio extends HashMap<String, IBankAccount> implements
        IAccountPortfolio {

    private IClient owner = null;

    public AccountPortfolio(IClient owner) {
        this.owner = owner;
        this.getOwner().setAccountPortfolio(this);
    }

    public void addAccount(IBankAccount bankAccount) {
        this.put("" + bankAccount.getAccountNumber(), bankAccount);
    }

    public Collection<IBankAccount> getAllAccounts() {
        return this.values();
    }

    public IBankAccount getAccount(String accountNumber) {
        return this.get(accountNumber);
    }

    public void setOwner(IClient client) throws AccountPortfolioExcpetion {

        if (client.getAccountPortfolio() != null) {
            throw new AccountPortfolioExcpetion("Client " + client.getName()
                    + " " + client.getAddress() + " " + client.getZipCode()
                    + " " + client.getCity()
                    + " already has an accountportfolio.");
        }

        this.owner = client;
    }

    public IClient getOwner() {
        return this.owner;
    }

}