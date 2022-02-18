package se.iths.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.entity.Item;

import java.net.URI;
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

    @Path("test")
    @GET
    public Item getItem(Item item) {
        return  new Item();
        // returns an automatic JSON object of the Item, with default response status codes e.g. 200
        // applies for PATCH, POST & DELETE also
    }
}
