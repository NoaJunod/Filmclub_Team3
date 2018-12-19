package ch.bbw.filmclub.team3.pc2.restful;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/greet")
public class RestService {
    private static int counter;
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNameInJSON(@PathParam("name") String name) {
        counter++;
        return "{Helloooo it's youuu " + name + "!: Number " + counter + " }";
    }
}
