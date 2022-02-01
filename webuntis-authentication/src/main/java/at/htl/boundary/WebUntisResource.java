package at.htl.boundary;

import at.htl.service.WebUntisService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/untis")
public class WebUntisResource {
    @Inject
    WebUntisService webUntisService;

    @POST
    @Path("auth")
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticateUser(@FormParam("userName") String userName, @FormParam("password") String password) {
        return Response.ok(webUntisService.authenticateUser(userName, password)).build();
    }


}
