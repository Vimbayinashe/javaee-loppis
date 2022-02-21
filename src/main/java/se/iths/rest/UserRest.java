package se.iths.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.entity.User;
import se.iths.service.UserService;

import java.util.List;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {

    @Inject
    UserService userService;

    @Path("")
    @POST
    public Response createUser(User user) {
        User userResult = userService.createUser(user);
        return Response.ok(userResult).build();
    }

    @Path("")
    @GET
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return  Response.ok(users).build();
    }

    @Path("{id}")
    @GET
    public Response getUser(@PathParam("id") Long id) {
        User foundUser = userService.findUserById(id);
        return Response.ok(foundUser).build();
    }

}
