package org.tix.soa2_1.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tix.soa2_1.exception.InvalidParameterException;
import org.tix.soa2_1.service.BookingService;

@Path("/booking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    BookingService bookingService;


    @POST
    @Path("/sell/discount/{ticket-id}/{person-id}/{discount}")
    public Response sellDiscount(@PathParam("ticket-id") String ticketId,
                                         @PathParam("person-id") String personId,
                                         @PathParam("discount") String discount){
        try {
            Long ticketIdLong = Long.parseLong(ticketId);
            Long personIdLong = Long.parseLong(personId);
            float discountFloat= Float.parseFloat(discount);
            return bookingService.setDiscountForTicket(ticketIdLong,personIdLong);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Incorrect format of input data");
        }
    }

    @PUT
    @Path("/person/{person-id}/cancel")
    public Response cancelAllBookingForPerson(@PathParam("person-id") String personId){
        try {
            Long personIdLong = Long.parseLong(personId);
            return bookingService.removeAllTicketsFromPerson(personIdLong);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
