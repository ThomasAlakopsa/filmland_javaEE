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
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user){

        //get user input: username, password
        //sent to login service
        //if true respond login succesfull
        //if false respond login failed
        boolean status = loginService.checkCredentials(user);

        if (status){
            return Response.ok("Successfully logged in!").build();
        }else{
            return Response.ok("Failed to login!").build();
        }
    }
}
