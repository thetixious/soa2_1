package org.tix.business.service;


import jakarta.ejb.Remote;
import jakarta.ws.rs.core.Response;

@Remote
public interface BookingServiceInt {

     Response setDiscountForTicket(Long ticketIdLong, Long personIdLong);
     Response removeAllTicketsFromPerson(Long personIdLong);
}
