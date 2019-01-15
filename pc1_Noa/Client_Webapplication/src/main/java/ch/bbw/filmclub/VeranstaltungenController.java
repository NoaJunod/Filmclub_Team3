/*
 * Author: Lukas Meili
 */
package ch.bbw.filmclub;

import ch.bbw.film.Film;
import ch.bbw.filmclub.model.veranstaltung.FilmVeranstaltungResponse;
import ch.bbw.filmclub.model.veranstaltung.Veranstaltung;
import ch.bbw.filmclub.model.veranstaltung.VeranstaltungenManager;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class VeranstaltungenController {
    private List<Veranstaltung> veranstaltungen;
    private Film film;
    private String error;

    public VeranstaltungenController() {
        //get which film from get request
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        long filmID = Long.parseLong(params.get("filmID"));

        FilmVeranstaltungResponse fVR = VeranstaltungenManager.getVeranstalungenFromApi(filmID);
        //null value handling
        if(fVR == null){
            error = "No film with this id could be found";
        } else {
            film = fVR.getFilm();
            veranstaltungen = fVR.getVeranstaltungen();
        }
    }

    public List<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public Film getFilm() {
        return film;
    }

    public String getError() {
        return error;
    }
}
