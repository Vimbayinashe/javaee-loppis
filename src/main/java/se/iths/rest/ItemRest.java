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
    public Response getItem(@PathParam("id") Long id) {
        Item foundItem = itemService.findItemById(id);
        if (foundItem == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with ID " +  id + " not found.").type(MediaType.TEXT_PLAIN_TYPE).build());
            //preferable to send this error message as a JSON object
            //possible solution: an error class that sends error messages (use injection as itemService?)
        }
        return Response.ok(foundItem).build();
    }

    @Path("getall")
    @GET
    public Response getAllItems() {
        List<Item> foundItems = itemService.getAllItems();
        return Response.ok(foundItems).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteItem(@PathParam("id") Long id) {
        itemService.deleteItem(id);
        return Response.ok().build();
    }

    @Path("getallbycategory")
    @GET
    public Response getAllItemsByCategory(@QueryParam("category") String category) {
        //Här finns logik som filtrerar ut alla items efter vald kategori

        String responseString = "Here is a list with all the items in category: " + category;
        return Response.ok(responseString).type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("updatename/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("name") String name) {
        Item updatedItem = itemService.updateName(id, name);
        return Response.ok(updatedItem).build();
    }

}
