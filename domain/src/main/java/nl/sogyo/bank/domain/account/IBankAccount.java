package nl.sogyo.bank.domain.account;

import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.NotAValidTransferAccountException;

public interface IBankAccount {

    char REKENINGCOURANT = 'C';
    char SPAARREKENING = 'S';
    char EFFECTENREKENING = 'B';

    /**
     * Deposit money into a bankaccount.
     *
     * @param amount Amount to deposit.
     * @return Current total of the account.
     */
    double deposit(double amount);

    /**
     * Withdraw money from a bankaccount.
     *
     * @param amount Amount to withdraw
     * @return Current total of the account.
     */
    double withdraw(double amount);

    /**
     * Returns the owner of the account
     *
     * @return Owner of the account
     */
    IClient getOwner();

    /**
     * Sets the owner of the account
     *
     * @param owner Owner of the account
     */
    void setOwner(IClient owner);

    /**
     * Returns the accountnumber.
     *
     * @return Number of the account
     */
    String getAccountNumber();

    /**
     * Returns the current balance for the account.
     *
     * @return Current balance for the account.
     */
    double getBalance();

    /**
     * Returns the type of bankaccount belonging to this account.
     *
     * @return The type of bankaccount belonging to this account.
     */
    BankAccountType getType();

    /**
     * Transfers money from the current account to the account used
     * to call this method.
     *
     * @param amount  The amount to transfer.
     * @param account Account to transfer money to
     * @throws NotAValidTransferAccountException that the account to transfer money to is not
     *                                           a valid account al all or is an account that cannot be used to
     *                                           transfer money to because it is of the wrong type.
     */
    void moneyTransfer(double amount, IBankAccount account) throws NotAValidTransferAccountException;

}