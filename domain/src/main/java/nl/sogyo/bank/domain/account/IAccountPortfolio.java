package nl.sogyo.bank.domain.account;

import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.AccountPortfolioExcpetion;

import java.util.Collection;

public interface IAccountPortfolio {

    void addAccount(IBankAccount bankAccount);

    Collection<IBankAccount> getAllAccounts();

    IBankAccount getAccount(String accountNumber);

    void setOwner(IClient client) throws AccountPortfolioExcpetion;

    IClient getOwner();

}