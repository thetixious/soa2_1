package org.tix.soa2_1.controller;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.ws.rs.PathParam;

@WebService
public interface BookingController {
    @WebMethod
    String test(String name);

    @WebMethod
    String sellDiscount(String ticketId,
                        String personId,
                        String discount);

    @WebMethod
    String cancelAllBookingForPerson( String personId);


}
