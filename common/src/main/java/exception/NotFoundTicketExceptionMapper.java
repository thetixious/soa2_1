package exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundTicketExceptionMapper implements ExceptionMapper<NotFoundTicketException> {

    @Override
    public Response toResponse(NotFoundTicketException exception) {
        String jsonResponse = String.format(
                "{\"code\": \"%d\", \"message\": \"%s\"}",
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage() != null ? exception.getMessage() : "Ticket or user not found"
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(jsonResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
