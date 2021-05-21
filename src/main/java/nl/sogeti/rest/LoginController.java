package nl.sogeti.rest;

import nl.sogeti.dto.LoginRequestDTO;
import nl.sogeti.model.User;
import nl.sogeti.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO user){
        return loginService.checkCredentials(user) ?
                Response.ok("Succesful login!").build() : Response.ok("Failed login!").build();
    }
}
