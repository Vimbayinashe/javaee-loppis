package se.iths.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
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

    @Path("test2")
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

    @Path("test3")
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
        throw new NotFoundException();          // Exception is handled by its Mapper using @Provided annotation
    }

    @Path("{id}")
    @GET
    public Response generateCodes(@PathParam("id") int id) {
        // String id parameter -> accepts both strings & int
       return Response.ok(id).build();
    }

//    @Path("{id}")
//    @GET
//    public Response generateCodes(@PathParam("id") int id, @QueryParam("orderBy") String orderBy,
//                                  @QueryParam("limit") String limit) {
//       return Response.ok("ID: " + id + ", orderBy " + orderBy + ", limit " + limit).build();
//    }

    @Path("")
    @GET
    public Response generateCodes( @QueryParam("orderBy") String orderBy, @QueryParam("limit") String limit) {
        return Response.ok( ", orderBy " + orderBy + ", limit " + limit).build();
    }

    @Path("")
    @POST
//    @Produces(MediaType.TEXT_PLAIN)     //override class's defaults
//    @Consumes(MediaType.TEXT_PLAIN)
    public Response addNew(Item item, @Context HttpHeaders headers) {
        // data is sent as a JSON object that is converted to an Item object/ItemDto (Item Data Transfer Object)
        // an EntityProvider (@Provider) can be used to map JSON to the desired POJO

        // return Response.ok(headers.getHeaderString("X-Myheader")).build();
        return Response.ok(item).build();
    }

    // @HeaderParam("Header-Name") e.g. @HeaderParam("X-Myheader") String myHeader
    // @CookieParam()
    // @Context HttpHeaders headers -> get all headers

    @Path("{id}")
    @PATCH
    public Response update(@PathParam("id") int id, Item item) {
        //hämta befintligt Item med angivet id från itemService
        Item oldItem = new Item();
        oldItem.setName("Kalle");
        oldItem.setCategory("cars");

//        if(!item.getName().isEmpty()) {
            oldItem.setName(item.getName());
        return Response.ok(oldItem).build();
    }

    // PATCH requires some properties of an object vs PUT requires the complete replacement object
}
