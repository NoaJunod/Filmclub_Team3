package ch.bbw.filmclub;

import ch.bbw.film.Film;
import ch.bbw.rmi.FilmList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.UndeclaredThrowableException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Throwable {
        /*FilmList list = null;
        try {
            list = (FilmList) Naming.lookup("//172.25.22.30/xml");
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.out.println("4");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("3");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("2");
        }

        ArrayList<Film> filme = new ArrayList<>();
        filme.add(new Film(1, "I am the one", "Gay", "Mo Bamba", 6969, 69666420, "PewNews"));
        try {
            System.out.println(list.getResponse());
            list.exportFilmList(filme);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("1");
        } catch (UndeclaredThrowableException e) {
            throw e.getCause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        FilmsucheViewController filmsucheViewController = new FilmsucheViewController();

        filmsucheViewController.setDuration(90);
        filmsucheViewController.setYear(1992);
        filmsucheViewController.setTitle("");
        filmsucheViewController.setDirector("");
        filmsucheViewController.setFormat("-");
        filmsucheViewController.setDistributor("");

        filmsucheViewController.search();

        JSONParser parser = new JSONParser();
    }
}
