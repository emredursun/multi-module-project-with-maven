package nl.sogyo.bank.domain.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String msg) {
        super("Account not found. Search criteria used :" + msg);
    }

}