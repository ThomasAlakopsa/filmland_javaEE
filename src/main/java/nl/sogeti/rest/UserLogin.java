package nl.sogeti.rest;

import nl.sogeti.model.User;
import nl.sogeti.repository.UserRepository;
import nl.sogeti.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/login")
public class UserLogin {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user){
        return loginService.checkCredentials(user) ?
                Response.ok("Succesful login!").build() : Response.ok("Failed login!").build();
    }
}