package ro.danut.exception;

public class NotEnoughAttributeException extends RuntimeException{
    public NotEnoughAttributeException(final String message) {
        super(message);
    }
}
