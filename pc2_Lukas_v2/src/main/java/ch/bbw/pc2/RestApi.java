package ch.bbw.pc2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greet")
public class RestApi {
    private static int counter;
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNameInJSON(@PathParam("name") String name) {
        counter++;
        return "{Helloooo it's youuu " + name + "!: Number " + counter + " }";
    }
}
