package zut.wi.streamingservice.exceptions;

public class EmailHasAlreadyBeenConfirmed extends RuntimeException {
    public EmailHasAlreadyBeenConfirmed(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailHasAlreadyBeenConfirmed(String message) {
        super(message);
    }
}
