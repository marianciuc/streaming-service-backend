package zut.wi.streamingservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import zut.wi.streamingservice.dto.response.ExceptionResponse;
import zut.wi.streamingservice.exceptions.JwtTokenExpiredException;
import zut.wi.streamingservice.exceptions.NotFoundException;
import zut.wi.streamingservice.exceptions.PaymentDeclinedError;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(PaymentDeclinedError.class)
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    protected ResponseEntity<Object> handlePaymentDeclinedError(PaymentDeclinedError ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .statusCode(HttpStatus.PAYMENT_REQUIRED.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
    }

    @ExceptionHandler(JwtTokenExpiredException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object> handleExpiredJwtException(JwtTokenExpiredException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}