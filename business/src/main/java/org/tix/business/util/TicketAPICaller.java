package org.tix.business.util;

import DTO.TicketForUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exception.InvalidParameterException;
import exception.NotFoundTicketException;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.TicketForResponse;

import java.util.logging.Logger;


@Stateless
@Dependent
public class TicketAPICaller {
    private Client client;
    private final String serviceUrl = "https://haproxy:20000/tickets";
    private static final Logger logger = Logger.getLogger(TicketAPICaller.class.getName());

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

    public TicketForResponse getById(Long ticketIdLong) {

        String targetPath = serviceUrl + "/" + ticketIdLong;


        try {
            CertificatesConfiguration.disableSslVerification();
            client = ClientBuilder.newClient();
            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).get();

            if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                logger.info(" ОШИБКААААА1");
                logger.info(String.valueOf(response.getStatus()));
                throw new NotFoundTicketException("Ticket or user not found");
            }
            String rawJson = response.readEntity(String.class);
            logger.info(rawJson);
            return mapper.readValue(rawJson, TicketForResponse.class);


        } catch (ProcessingException |
                 JsonProcessingException e) {
            throw new InvalidParameterException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            client.close();
        }

    }


    public void postByPersonId(TicketForUserDTO newTicket) {
        String targetPath = serviceUrl + "/" + "person";
        try {
            CertificatesConfiguration.disableSslVerification();
            client = ClientBuilder.newClient();
            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(newTicket, MediaType.APPLICATION_JSON));
            logger.info(response.toString());
            if (response.getStatus() !=Response.Status.OK.getStatusCode()) {
                logger.info(" ОШИБКААААА1");
                logger.info(String.valueOf(response.getStatus()));
                throw new NotFoundTicketException("Ticket or user not found");
            }
        } catch (ProcessingException e) {
            throw new InvalidParameterException("Проблема на этапе кола другого апи");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            client.close();
        }
    }

    public void removeAllByPersonId(Long personId) {
        String targetPath = serviceUrl + "/person/" + personId;

        try {
            CertificatesConfiguration.disableSslVerification();
            client = ClientBuilder.newClient();
            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).delete();
            logger.info(String.valueOf(response.getStatus()));
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                logger.info("офывдлаофлыд ОШИБКААААА2");
                throw new NotFoundTicketException("Ticket or user not found");
            }
        } catch (ProcessingException e) {
            throw new InvalidParameterException("Processing error while removing tickets for personId " + personId + ": " + e.getMessage());
        } catch (NotFoundTicketException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while removing tickets for personId " + personId, e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
