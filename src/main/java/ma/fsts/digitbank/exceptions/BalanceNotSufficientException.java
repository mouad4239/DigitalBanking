package ma.fsts.digitbank.exceptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String s) {
        super(s);
    }
}
