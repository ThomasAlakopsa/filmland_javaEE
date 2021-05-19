package nl.sogeti.rest;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/user")
public class UserEndpoints {

    @Inject
    private UserRepository userRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") @Min(1) Long id){
        User user = userRepository.find(id);

        if (user == null) {return Response.noContent().build();}

        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user, @Context UriInfo uriInfo) {
        user = userRepository.create(user);
        URI createURI = uriInfo.getBaseUriBuilder().path(user.getId().toString()).build();
        return Response.created(createURI).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") @Min(1) Long id) {
        userRepository.delete(id);
        return Response.noContent().build();
    }

}
