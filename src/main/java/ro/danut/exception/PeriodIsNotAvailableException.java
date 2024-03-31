package ro.danut.exception;

public class PeriodIsNotAvailableException extends RuntimeException{
    public PeriodIsNotAvailableException(final String message) {
        super(message);
    }
}
