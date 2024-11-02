package org.tix.soa2_1.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {
    @Override
    public Response toResponse(InvalidParameterException exception) {
        String jsonResponse = String.format(
                "{\"code\": \"%d\", \"message\": \"%s\"}",
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage() != null ? exception.getMessage() : "Validation Failed"
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(jsonResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
