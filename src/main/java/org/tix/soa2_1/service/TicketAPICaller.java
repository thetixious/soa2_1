package org.tix.soa2_1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tix.soa2_1.exception.InvalidParameterException;
import org.tix.soa2_1.model.TicketForResponse;
import org.tix.soa2_1.resource.DTO.TicketForUserDTO;

import java.util.logging.Logger;


@ApplicationScoped
public class TicketAPICaller {
    private Client client;
    private final String serviceUrl = "http://localhost:8080/tickets";
    private static final Logger logger = Logger.getLogger(TicketAPICaller.class.getName());

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

    public TicketForResponse getById(Long ticketIdLong) {

        String targetPath = serviceUrl + "/" + ticketIdLong;


        try {
            client = ClientBuilder.newClient();
            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).get();

            String rawJson = response.readEntity(String.class);

            return mapper.readValue(rawJson, TicketForResponse.class);

        } catch (ProcessingException |
                 JsonProcessingException e) {
            throw new InvalidParameterException(e.getMessage());
        } finally {
            client.close();
        }

    }


    public void postByPersonId(TicketForUserDTO newTicket) {
        String targetPath = serviceUrl + "/" + "person";
        try {
            client = ClientBuilder.newClient();
            client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(newTicket, MediaType.APPLICATION_JSON));

        } catch (ProcessingException e) {
            throw new InvalidParameterException(e.getMessage());
        } finally {
            client.close();
        }
    }

    public void removeAllByPersonId(Long personId) {
        String targetPath = serviceUrl + "/person/" + personId;

        try {
            client = ClientBuilder.newClient();
            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).get();
            if (response.getStatus() != Response.Status.OK.getStatusCode())
                throw new InvalidParameterException("Person with that ID doesn't exist");
        } catch (ProcessingException e) {
            throw new InvalidParameterException(e.getMessage());
        } finally {
            client.close();
        }
    }
}
