package zut.wi.streamingservice.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import zut.wi.streamingservice.dto.response.ExceptionResponse;
import zut.wi.streamingservice.exceptions.PaymentDeclinedError;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse runtimeExceptionHandler(RuntimeException ex, WebRequest request) {
        return ExceptionResponse
                .builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(PaymentDeclinedError.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    public ExceptionResponse runtimeExceptionHandler(PaymentDeclinedError ex, WebRequest request) {
        return ExceptionResponse
                .builder()
                .statusCode(HttpStatus.PAYMENT_REQUIRED.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    public ExceptionResponse runtimeExceptionHandler(ExpiredJwtException ex, WebRequest request) {
        return ExceptionResponse
                .builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
    }
}