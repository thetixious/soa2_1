package org.tix.soa2_1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.tix.soa2_1.model.TicketForResponse;
import org.tix.soa2_1.resource.DTO.TicketForUserDTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class BookingService {
    @Inject
    TicketAPICaller ticketAPICaller;

    public Response setDiscountForTicket(Long ticketIdLong, Long personIdLong, Double discountDouble) {
        TicketForResponse ticket = ticketAPICaller.getById(ticketIdLong);
        TicketForUserDTO newTicket = new TicketForUserDTO();
        newTicket.setName(ticket.getName());
        newTicket.setPrice(ticket.getPrice()*discountDouble + ticket.getPrice() - ticket.getPrice()*discountDouble);
        newTicket.setTicketType(ticket.getType());
        newTicket.setCreationDate(ZonedDateTime.parse(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-M-d"))));
        newTicket.setCoordinates(ticket.getCoordinates());
        newTicket.setComment(ticket.getComment());
        newTicket.setPersonId(personIdLong);

        ticketAPICaller.postByPersonId(newTicket);
        return Response.ok().entity(newTicket).build();

    }


    public Response removeAllTicketsFromPerson(Long personIdLong) {
        ticketAPICaller.removeAllByPersonId(personIdLong);
        return Response.ok().entity("Бронирования удалены").build();
    }
}
