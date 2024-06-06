package zut.wi.streamingservice.exceptions;

public class PaymentDeclinedError extends RuntimeException{
    public PaymentDeclinedError(String message){
        super(message);
    }
}
