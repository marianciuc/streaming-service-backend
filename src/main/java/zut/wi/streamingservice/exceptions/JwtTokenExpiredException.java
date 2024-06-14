package zut.wi.streamingservice.exceptions;

public class JwtTokenExpiredException extends RuntimeException{
    public JwtTokenExpiredException(String message, Throwable cause){
        super(message, cause);
    }

    public JwtTokenExpiredException(String message){
        super(message);
    }
}
