package zut.wi.streamingservice.exceptions;

public class SubscriptionAlreadyExistsException extends RuntimeException {
    public SubscriptionAlreadyExistsException(String message) {
        super(message);
    }
}