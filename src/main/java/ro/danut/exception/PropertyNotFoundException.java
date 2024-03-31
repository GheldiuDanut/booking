package ro.danut.exception;

public class PropertyNotFoundException extends RuntimeException{

    public PropertyNotFoundException(final String message) {
        super(message);
    }
}
