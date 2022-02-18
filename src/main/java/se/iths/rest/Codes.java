package se.iths.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.entity.Item;
import se.iths.exceptions.IAmATeapotException;

import java.time.Instant;
import java.util.Date;

@Path("codes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Codes {

    @Path("")
    @GET
    public Response generateCodes(Item item) {
        return Response.status(Response.Status.PARTIAL_CONTENT)
//        return Response.created(URI.create("/codes"))     //alternative
                .encoding("UTF-8")
                .expires(Date.from(Instant.now()))
                .build();
    // Response.created(location) -> location - the URI of the new resource. If a relative URI is supplied it will be
    // converted into an absolute URI by resolving it relative to the base URI
    }

    @Path("test1")
    @GET
    public Item getItem(Item item) {
        // Alt 1
        // return Response.ok(new Item()).build();  // allows a more customised response

        // Alt 2
        return  new Item();
        // returns an automatic JSON object of the Item, with default response status codes e.g. 200
        // applies for PATCH, POST & DELETE also
    }

    @Path("")
    @POST
    public Response getItem(Long id) {
        // Alt 1
        // return Response.status(Response.Status.NOT_FOUND).build();

        // Alt 2
//        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
//                .entity("Item with ID " +  id + " not found.").type(MediaType.TEXT_PLAIN_TYPE).build());

        // Alt 3
        // advisable: create own Exception class that extends WebApplicationException
        // maybe a class that throws these errors with error message for us (Exceptions Wrapper implementation & Exceptions example)

        //throw new IAmATeapotException();
        throw new NotFoundException();
    }
}
