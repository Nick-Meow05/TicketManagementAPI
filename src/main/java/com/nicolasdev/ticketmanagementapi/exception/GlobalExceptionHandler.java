package com.nicolasdev.ticketmanagementapi.exception;

import com.nicolasdev.ticketmanagementapi.entity.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    }
}
