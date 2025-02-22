package org.tix.soa2_1.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.tix.soa2_1.model.TicketForResponse;
import org.tix.soa2_1.resource.DTO.TicketForUserDTO;

@WebService(targetNamespace = "http://service.soa2_1.tix.org/")
public interface TicketSoapProxyServiceInterface {

    @WebMethod
    TicketForResponse getTicketById(@WebParam(name = "ticketId") Long ticketId);

    @WebMethod
    void createTicket(@WebParam(name = "ticketData") TicketForUserDTO ticketData);

    @WebMethod
    void removeAllByPersonId(@WebParam(name = "personId") Long personId);
}