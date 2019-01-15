/*
 * Author: Lukas Meili
 */
package ch.bbw.filmclub.model.veranstaltung;

import ch.bbw.film.Film;

import java.util.List;

public class FilmVeranstaltungResponse {
    private Film film;
    private List<Veranstaltung> veranstaltungen;

    public FilmVeranstaltungResponse(Film film, List<Veranstaltung> veranstaltungen) {
        this.film = film;
        this.veranstaltungen = veranstaltungen;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public void setVeranstaltungen(List<Veranstaltung> veranstaltungen) {
        this.veranstaltungen = veranstaltungen;
    }
}
