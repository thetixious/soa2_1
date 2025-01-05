package org.tix.soa2_1.exception;

public class NotFoundTicketException extends RuntimeException{
    public NotFoundTicketException(String message) {
        super(message);
    }

}
