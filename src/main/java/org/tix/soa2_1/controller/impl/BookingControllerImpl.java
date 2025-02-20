package org.tix.soa2_1.controller.impl;


import jakarta.inject.Inject;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.tix.soa2_1.controller.BookingController;
import org.tix.soa2_1.exception.InvalidParameterException;
import org.tix.soa2_1.service.BookingService;

@WebService
public class BookingControllerImpl implements BookingController {
    @Inject
    BookingService bookingService;
    @Override
    public String test(String name) {
        return "Hello" + name;
    }

    @Override
    public String sellDiscount(@WebParam(name = "ticketId") String ticketId,@WebParam(name = "personId") String personId,@WebParam(name = "discount") String discount) {
        try {
            Long ticketIdLong = Long.parseLong(ticketId);
            Long personIdLong = Long.parseLong(personId);
            float discountFloat= Float.parseFloat(discount);
            return bookingService.setDiscountForTicket(ticketIdLong,personIdLong).toString();
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Incorrect format of input data");
        }
    }

    @Override
    public String cancelAllBookingForPerson(@WebParam(name = "personId") String personId) {
        try {
            Long personIdLong = Long.parseLong(personId);
            return bookingService.removeAllTicketsFromPerson(personIdLong).toString();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
