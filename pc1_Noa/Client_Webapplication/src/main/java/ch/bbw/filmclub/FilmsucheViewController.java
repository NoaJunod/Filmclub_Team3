package ch.bbw.filmclub;


import ch.bbw.film.Film;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Noa Junod
 */

@Named
@RequestScoped
public class FilmsucheViewController {

    private String title, format, director, distributor;
    private int  year, duration;

    private Film[] filmsArray;

    private ArrayList<Film> films;

    @Inject
    private Filmclub filmclub;

    public FilmsucheViewController() {
        films = new ArrayList<>();
        filmclub = new Filmclub();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean checkIfFilledIn(){
        int counter = 0;
        if(!title.equals("")){
            filmclub.setTitle(title);
            counter++;
        } else {
            filmclub.setTitle("");
        }
        if(!format.equals("-")){
            filmclub.setFormat(format);
            counter++;
        } else {
            filmclub.setFormat("");
        }
        if(duration > 0){
            filmclub.setDuration(duration);
            counter++;
        } else {
            filmclub.setDuration(0);
        }
        if(!director.equals("")){
            filmclub.setDirector(director);
            counter++;
        } else {
            filmclub.setDirector("");
        }
        if(!distributor.equals("")){
            filmclub.setDistributor(distributor);
            counter++;
        } else {
            filmclub.setDistributor("");
        }
        if(year > 0){
            filmclub.setYear(year);
            counter++;
        } else {
            filmclub.setYear(0);
        }
        return counter >= 2;
    }

    public Film[] getFilmsArray() {
        return filmsArray;
    }

    public void setFilmsArray(Film[] filmsArray) {
        this.filmsArray = filmsArray;
    }

    public void search() throws IOException {
        if(checkIfFilledIn()) {
            filmclub.search();
            System.out.println(filmclub.getTitle());
        }
    }

    @PostConstruct
    public void init(){
        filmclub.initialise();
    }
}
