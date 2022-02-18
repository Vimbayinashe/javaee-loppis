package se.iths.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IAmATeapotMapper implements ExceptionMapper<IAmATeapotException> {

    @Override
    public Response toResponse(IAmATeapotException e) {
        return Response.status(418, "I am a teapot!")   // Response.status(418)
                .entity("I'm a teapot!").type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}


// @Provider tillhandahåller en implementation av en interface