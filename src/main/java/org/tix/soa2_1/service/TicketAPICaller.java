package org.tix.soa2_1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.xml.ws.Service;
import org.tix.soa2_1.model.TicketForResponse;
import org.tix.soa2_1.resource.DTO.TicketForUserDTO;

import javax.xml.namespace.QName;
import java.net.URL;


@ApplicationScoped
public class TicketAPICaller {
    private static final String WSDL_URL = "http://localhost:8081/soa2_1-1.0-SNAPSHOT/TicketSoapProxyServiceService?wsdl";
    private static final QName SERVICE_NAME = new QName("http://service.soa2_1.tix.org/", "TicketSoapProxyServiceService");

    private TicketSoapProxyServiceInterface proxyService;

    public TicketAPICaller() {
        try {
            CertificatesConfiguration.disableSslVerification();
            URL wsdlLocation = new URL(WSDL_URL);
            Service service = Service.create(wsdlLocation, SERVICE_NAME);
            proxyService = service.getPort(TicketSoapProxyServiceInterface.class); // Используем интерфейс!
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при подключении к SOAP-прокси", e);
        }
    }


    public TicketForResponse getById(Long ticketId) {
        return proxyService.getTicketById(ticketId);
    }

    public void postByPersonId(TicketForUserDTO ticketData) {
         proxyService.createTicket(ticketData);
    }

    public void removeAllByPersonId(Long personIdLong) {
        proxyService.removeAllByPersonId(personIdLong);
    }


}
//public class TicketAPICaller {
//
//    private Client client;
//    private final String serviceUrl = "https://localhost:8000/tickets";
//    private static final Logger logger = Logger.getLogger(TicketAPICaller.class.getName());
//
//    private static final ObjectMapper mapper = new ObjectMapper()
//            .registerModule(new JavaTimeModule())
//            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
//
//    public TicketForResponse getById(Long ticketIdLong) {
//
//        String targetPath = serviceUrl + "/" + ticketIdLong;
//
//
//        try {
//            CertificatesConfiguration.disableSslVerification();
//            client = ClientBuilder.newClient();
//            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).get();
//
//            if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
//                logger.info(" ОШИБКААААА1");
//                logger.info(String.valueOf(response.getStatus()));
//                throw new NotFoundTicketException("Ticket or user not found");
//            }
//            String rawJson = response.readEntity(String.class);
//            logger.info(rawJson);
//            return mapper.readValue(rawJson, TicketForResponse.class);
//
//
//        } catch (ProcessingException |
//                 JsonProcessingException e) {
//            throw new InvalidParameterException(e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            client.close();
//        }
//
//    }
//
//
//    public void postByPersonId(TicketForUserDTO newTicket) {
//        String targetPath = serviceUrl + "/" + "person";
//        try {
//            CertificatesConfiguration.disableSslVerification();
//            client = ClientBuilder.newClient();
//            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(newTicket, MediaType.APPLICATION_JSON));
//            logger.info(response.toString());
//            if (response.getStatus() !=Response.Status.OK.getStatusCode()) {
//                logger.info(" ОШИБКААААА1");
//                logger.info(String.valueOf(response.getStatus()));
//                throw new NotFoundTicketException("Ticket or user not found");
//            }
//        } catch (ProcessingException e) {
//            throw new InvalidParameterException("Проблема на этапе кола другого апи");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            client.close();
//        }
//    }
//
//    public void removeAllByPersonId(Long personId) {
//        String targetPath = serviceUrl + "/person/" + personId;
//
//        try {
//            CertificatesConfiguration.disableSslVerification();
//            client = ClientBuilder.newClient();
//            Response response = client.target(targetPath).request(MediaType.APPLICATION_JSON_TYPE).delete();
//            logger.info(String.valueOf(response.getStatus()));
//            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
//                logger.info("офывдлаофлыд ОШИБКААААА2");
//                throw new NotFoundTicketException("Ticket or user not found");
//            }
//        } catch (ProcessingException e) {
//            throw new InvalidParameterException("Processing error while removing tickets for personId " + personId + ": " + e.getMessage());
//        } catch (NotFoundTicketException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new RuntimeException("Unexpected error while removing tickets for personId " + personId, e);
//        } finally {
//            if (client != null) {
//                client.close();
//            }
//        }
//    }
//}
