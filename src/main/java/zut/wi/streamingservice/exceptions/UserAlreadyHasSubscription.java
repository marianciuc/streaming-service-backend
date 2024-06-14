package zut.wi.streamingservice.exceptions;

public class UserAlreadyHasSubscription extends RuntimeException{
    public UserAlreadyHasSubscription(String message, Throwable cause){
        super(message, cause);
    }

    public UserAlreadyHasSubscription(String message){
        super(message);
    }
}
