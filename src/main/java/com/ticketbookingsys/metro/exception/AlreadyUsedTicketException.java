package com.ticketbookingsys.metro.exception;

public class AlreadyUsedTicketException extends  RuntimeException {
    public AlreadyUsedTicketException(String message) {
        super(message);
    }
}
