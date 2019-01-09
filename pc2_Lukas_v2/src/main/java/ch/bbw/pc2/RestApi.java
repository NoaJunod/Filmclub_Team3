package ch.bbw.pc2;

import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ch.bbw.pc2.model.db.DBSession;

@Path("/greet")
public class RestApi {
    private static int counter;
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNameInJSON(@PathParam("name") String name) {
        counter++;
        //DBSession.getSession().createQuery(new CriteriaQuery<Object>() {
        //});
        return "{ Helloooo it's youuu " + name + "!: Number " + counter + " }";
    }
}
