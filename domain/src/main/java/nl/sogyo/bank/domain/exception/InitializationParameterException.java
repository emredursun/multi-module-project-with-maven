package nl.sogyo.bank.domain.exception;

public class InitializationParameterException extends Exception {

    public InitializationParameterException(String msg) {
        super(
                "One or more of the following arguments is either \"null\" of empty when trying to instantiate "
                        + msg);
    }

}