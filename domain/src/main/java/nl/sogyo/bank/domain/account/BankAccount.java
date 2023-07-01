package nl.sogyo.bank.domain.account;

import nl.sogyo.bank.domain.client.IClient;
import nl.sogyo.bank.domain.exception.InitializationParameterException;

import java.util.Objects;

enum BankAccountType {
    REKENINGCOURANT, SPAARREKENING, EFFECTENREKENING
}

public abstract class BankAccount implements IBankAccount {

    /**
     * Total number of accounts instantiated.
     */
    private static int totalNumberOfAccounts = 0;

    private String accountNumber = "-1";

    private double balance = 0;

    private IClient owner = null;

    private BankAccountType type;

    private class Formatter {
        private String format() {
            return getOwner().getName() + " (" + getAccountNumber() + "): " + getBalance();
        }
    }

    /**
     * Constructor
     *
     * @param accountNumber Number of the new account.
     * @param owner         Client who owns the account.
     */
    public BankAccount(String accountNumber, IClient owner)
            throws InitializationParameterException {
        if (accountNumber == null || "".equals(accountNumber) || owner == null) {
            throw new InitializationParameterException(this.getClass().getName() + ": accountNumber, owner");
        }

        this.setAccountNumber(accountNumber);
        this.setOwner(owner);

        BankAccount.adjustNumberOfAccounts();
    }

    /**
     * Deposit money into a bankaccount
     *
     * @param amount Amount to deposit
     * @return Current total of the account.
     */
    public double deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
        return this.getBalance();
    }

    /**
     * Withdraw money from a bankaccount.
     *
     * @param amount Amount to withdraw
     * @return Current total of the account.
     */
    public double withdraw(double amount) {
        if (amount > 0) {
            this.balance -= amount;
        }
        return this.getBalance();
    }

    /**
     * Returns the owner of the account.
     *
     * @return Client who owns the account.
     * @see IBankAccount#getOwner()
     */
    public IClient getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner of the account.
     *
     * @param owner Client who onws the account.
     * @see IBankAccount#setOwner(IClient)
     */
    public void setOwner(IClient owner) {
        this.owner = owner;
    }

    /**
     * Returns the accountnumber for the account.
     *
     * @return Accountnumber for the account.
     * @see IBankAccount#getAccountNumber()
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Sets the accountnumber for this account
     *
     * @param accountNumber Accountnumber for this account.
     */
    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Returns the balance for the account.
     *
     * @return Balance for the account.
     * @see IBankAccount#getBalance()
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Addes 1 to the total number of accounts created.
     */
    protected static synchronized void adjustNumberOfAccounts() {
        BankAccount.totalNumberOfAccounts++;
    }

    /**
     * Subtracts 1 from the total number of accounts active.
     *
     * @see Object#finalize()
     */
    protected synchronized void finalize() {
        BankAccount.totalNumberOfAccounts--;
    }

    /**
     * Returns the total number of accounts present.
     *
     * @return Total number of accounts present.
     */
    public static int getTotalNumberOfAccounts() {
        return BankAccount.totalNumberOfAccounts;
    }

    /**
     * Returns the type of bankaccount this is.
     *
     * @return The type of bankaccount this is.
     */
    public BankAccountType getType() {
        return this.type;
    }

    /**
     * Sets what type of bankaccount this is
     *
     * @param type Type of account.
     */
    protected void setType(BankAccountType type) {
        this.type = type;
    }

    /**
     * Returns the String representation of this object.
     *
     * @return The String representation of this object.
     * @see Object#toString()
     */
    public String toString() {
        Formatter formatter = new Formatter();
        return formatter.format();
    }

    /**
     * Checks to see if two objects are equal to eachother.
     *
     * @return True if objects are equal to eachother.<br />
     * False if objects are NOT equal to eachother.
     * @see Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BankAccount account = (BankAccount) object;
        return Double.compare(account.balance, balance) == 0 &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(owner, account.owner) && type == account.type;
    }

}