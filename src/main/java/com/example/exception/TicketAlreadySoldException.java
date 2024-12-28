package com.example.exception;
public class TicketAlreadySoldException extends RuntimeException {
    public TicketAlreadySoldException(String message) {
        super(message);
    }
}