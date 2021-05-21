package nl.sogeti.rest;

import nl.sogeti.dto.UserDTO;
import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;
import nl.sogeti.services.UserService;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/user")
public class UserEndpoints {

    @Inject
    private UserService userService;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") @Min(1) Long id){

        UserDTO user = userService.getUser(id);
        if (user == null) {return Response.noContent().build();}
        return Response.ok(user).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        List<UserDTO> foo = userService.getAllUsers();
        return Response.ok(foo).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        userService.createUser(user);
        return Response.ok("Account succesfully created!").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") @Min(1) Long id) {

        userService.deleteUser(id);
        return Response.noContent().build();
    }

}
