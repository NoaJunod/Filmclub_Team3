/*
* Author: Lukas Meili
 */
package ch.bbw.pc2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.bbw.pc2.model.db.DBSession;
import ch.bbw.pc2.model.db.Member;
import ch.bbw.pc2.restapimodel.GETFilm;
import ch.bbw.pc2.restapimodel.PUTFilm;
import org.hibernate.Session;

import java.util.List;

/**
 * RestAPI class. Handles every request and redirects it.
 * Base path is "/film".
 */
@Path("/film")
public class RestApi {
    /**
     * Redirects a GET "/film/query" request to @{@link GETFilm}.
     *
     * @param title
     * @param format
     * @param director
     * @param yearOfProduction
     * @param durationInMin
     * @param distributor
     * @return
     */
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFilms(@QueryParam("title") String title,
                           @QueryParam("format") String format,
                           @QueryParam("director") String director,
                           @QueryParam("yearOfProduction") int yearOfProduction,
                           @QueryParam("duration") int durationInMin,
                           @QueryParam("distributor") String distributor) {
        return GETFilm.getFilms(title, format, director, yearOfProduction, durationInMin, distributor).toString();
    }

    /**
     * Redirects a PUT "/film/add" request to @{@link PUTFilm}.
     *
     * @param title
     * @param format
     * @param director
     * @param yearOfProduction
     * @param durationInMin
     * @param distributor
     * @return
     */
    @PUT
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addFilm(@FormParam("title") String title,
                          @FormParam("format") String format,
                          @FormParam("director") String director,
                          @FormParam("yearOfProduction") int yearOfProduction,
                          @FormParam("duration") int durationInMin,
                          @FormParam("distributor") String distributor) {
        return PUTFilm.addFilm(title, format, director, yearOfProduction, durationInMin, distributor).toString();
    }
}
