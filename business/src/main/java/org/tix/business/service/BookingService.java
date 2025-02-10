package org.tix.business.service;

import DTO.TicketForUserDTO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import model.TicketForResponse;
import org.tix.business.util.TicketAPICaller;

import java.time.ZonedDateTime;
import java.util.logging.Logger;


@Stateless
public class BookingService implements BookingServiceInt {

    @Inject
    TicketAPICaller ticketAPICaller;
    private static final Logger logger = Logger.getLogger(BookingService.class.getName());
    @Override
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

    @Override
    public Response removeAllTicketsFromPerson(Long personIdLong) {
        ticketAPICaller.removeAllByPersonId(personIdLong);

        return Response.ok().entity("Бронирования удалены").build();
    }
}
