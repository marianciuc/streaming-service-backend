package zut.wi.streamingservice.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import zut.wi.streamingservice.dto.response.ExceptionResponse;
import zut.wi.streamingservice.exceptions.NotFoundException;
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
    public ExceptionResponse paymentExceptionHandler(PaymentDeclinedError ex, WebRequest request) {
        return ExceptionResponse
                .builder()
                .statusCode(HttpStatus.PAYMENT_REQUIRED.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    public ExceptionResponse expiredJwtExceptionHandler(ExpiredJwtException ex, WebRequest request) {
        return ExceptionResponse
                .builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    public ExceptionResponse notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        return ExceptionResponse
                .builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
    }
}