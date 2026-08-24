package com.nicolasdev.ticketmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// Catches the Exception
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
            (TicketNotFoundException.class)
    public ResponseEntity<String>
    handleTicketNotFound(
            TicketNotFoundException exception){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }@ExceptionHandler
            (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidationException(MethodArgumentNotValidException exception){

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    String field = error.getField();
                    String message = error.getDefaultMessage();

                    if(!errors.containsKey(field)
                        || message.contains("required")){

                        errors.put(field,message);
                    }
                });
        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}
