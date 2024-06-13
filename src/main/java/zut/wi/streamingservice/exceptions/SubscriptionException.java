package zut.wi.streamingservice.exceptions;

public class SubscriptionException extends RuntimeException{
    public SubscriptionException(String message, Throwable cause){
        super(message, cause);
    }
}
