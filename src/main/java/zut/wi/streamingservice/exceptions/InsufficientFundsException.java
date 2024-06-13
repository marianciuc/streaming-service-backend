package zut.wi.streamingservice.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message, Throwable cause){
        super(message, cause);
    }

    public InsufficientFundsException(String message){
        super(message);
    }
}
