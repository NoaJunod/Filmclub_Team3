package ch.bbw.filmclub;


import ch.bbw.film.Film;
import ch.bbw.rmi.FilmList;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

@Named
@RequestScoped
public class FilmlistViewController {

    private ArrayList<Film> films;

    @Inject
    private Filmclub filmclub;

    public FilmlistViewController() {
        films = new ArrayList<>();
        filmclub = new Filmclub();
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public void export(){
        FilmList list = null;
        try {
            list = (FilmList) Naming.lookup("//172.25.22.30/xml");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(list.getResponse());
            list.exportFilmList(films);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init(){
        filmclub.initialise();
    }
}
