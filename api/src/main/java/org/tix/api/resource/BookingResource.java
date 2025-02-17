package org.tix.api.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tix.business.service.BookingService;
import org.tix.business.service.BookingServiceInt;
import org.tix.api.util.JndiTool;
import java.security.InvalidParameterException;
import java.util.logging.Logger;


@Path("/booking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {
    private static final Logger logger = Logger.getLogger(BookingService.class.getName());


    @POST
    @Path("/sell/discount/{ticket-id}/{person-id}/{discount}")
    public Response sellDiscount(@PathParam("ticket-id") String ticketId,
                                         @PathParam("person-id") String personId,
                                         @PathParam("discount") String discount){
        try {
            Long ticketIdLong = Long.parseLong(ticketId);
            Long personIdLong = Long.parseLong(personId);
            float discountFloat= Float.parseFloat(discount);
            getService().setDiscountForTicket(ticketIdLong,personIdLong);
            return Response.ok().build();
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Incorrect format of input data");
        }
    }

    @PUT
    @Path("/person/{person-id}/cancel")
    public Response cancelAllBookingForPerson(@PathParam("person-id") String personId){
        try {
            Long personIdLong = Long.parseLong(personId);
            getService().removeAllTicketsFromPerson(personIdLong);

            return Response.ok().build();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private BookingServiceInt getService(){
        return JndiTool.getFromContext(BookingServiceInt.class,
                "java:global/api-1.0-SNAPSHOT/BookingService!org.tix.business.service.BookingServiceInt");

    }
}
