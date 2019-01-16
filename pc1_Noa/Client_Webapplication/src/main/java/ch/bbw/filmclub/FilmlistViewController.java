package ch.bbw.filmclub;


import ch.bbw.film.Film;
import ch.bbw.filmclub.model.filmclub.Filmclub;
import ch.bbw.rmi.FilmList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Noa Junod
 */

@Named
@SessionScoped
public class FilmlistViewController implements Serializable {
    //@Inject
    private Filmclub filmclub;

    public FilmlistViewController() {
        //films = new ArrayList<>();
        filmclub = Filmclub.getInstance();
    }

    public ArrayList<Film> getFilms() {
        return filmclub.getFilms();
    }

    public void export(){
        System.out.println(filmclub.getFilms().get(0).getTitle());
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
            list.exportFilmList(filmclub.getFilms());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init(){
        //filmclub.initialise();
    }
}
