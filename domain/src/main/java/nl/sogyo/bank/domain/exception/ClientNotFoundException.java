package nl.sogyo.bank.domain.exception;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException(String msg) {
        super("Client not found. Search criteria used :" + msg);
    }

}