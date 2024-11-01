package org.tix.soa2_1.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tix.soa2_1.exception.InvalidParameterException;

@Path("/booking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {
    @POST
    @Path("/sell/discount/{ticket-id}/{person-id}/{discount}")
    public Response setDiscountForTicket(@PathParam("ticket-id") String ticketId,
                                         @PathParam("person-id") String personId,
                                         @PathParam("discount") String discount){
        try {
            Long ticketIdLong = Long.parseLong(ticketId);
            Long personIdLong = Long.parseLong(personId);
            Integer discountInt = Integer.parseInt(discount);

            return Response.ok().build();
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Incorrect format of input data");
        }
    }

    @PUT
    @Path("/person/{person-id}/cancel")
    public Response cancelAllBookingForPerson(@PathParam("person-id") String personId){
        return Response.ok().build();
    }

}
