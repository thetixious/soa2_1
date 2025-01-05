package org.tix.soa2_1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.tix.soa2_1.model.TicketForResponse;
import org.tix.soa2_1.resource.DTO.TicketForUserDTO;

import java.time.ZonedDateTime;
import java.util.logging.Logger;

@ApplicationScoped
public class BookingService {
    @Inject
    TicketAPICaller ticketAPICaller;
    private static final Logger logger = Logger.getLogger(BookingService.class.getName());

    public Response setDiscountForTicket(Long ticketIdLong, Long personIdLong) {
        TicketForResponse ticket = ticketAPICaller.getById(ticketIdLong);
        TicketForUserDTO newTicket = new TicketForUserDTO();
        newTicket.setName(ticket.getName());
        newTicket.setPrice(ticket.getPrice());
        newTicket.setType(ticket.getType());
        newTicket.setCreationDate(ZonedDateTime.now());
        newTicket.setCoordinates(ticket.getCoordinates());
        newTicket.setComment(ticket.getComment());
        newTicket.setPersonId(personIdLong);
        logger.info(newTicket.toString());
        ticketAPICaller.postByPersonId(newTicket);
        return Response.ok().entity(newTicket).build();

    }


    public Response removeAllTicketsFromPerson(Long personIdLong) {
        ticketAPICaller.removeAllByPersonId(personIdLong);
        return Response.ok().entity("Бронирования удалены").build();
    }
}
