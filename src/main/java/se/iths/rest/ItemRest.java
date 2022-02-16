package se.iths.rest;

import jakarta.ws.rs.core.MediaType;
import se.iths.entity.Item;
import se.iths.service.ItemService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemRest {

    @Inject
    ItemService itemService;

    @Path("new")
    @POST
    public Response createItem(Item item) {
        //layered architecture -> creating a service class that handles one stage vs handling everything in this method
        itemService.createItem(item);
        return Response.ok(item).build();
    }

    @Path("update")
    @PUT
    public Response updateItem(Item item) {
        itemService.updateItem(item);
        return Response.ok(item).build();
    }

    @Path("{id}")
    @GET
    public Item getItem(@PathParam("id") Long id) {
        return itemService.findItemById(id);
    }

    @Path("getall")
    @GET
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

}
