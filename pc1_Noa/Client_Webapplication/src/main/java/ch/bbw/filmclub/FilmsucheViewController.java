package ch.bbw.filmclub;


import ch.bbw.film.Film;
import ch.bbw.filmclub.model.filmclub.Filmclub;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Noa Junod
 */

@Named
@SessionScoped
public class FilmsucheViewController implements Serializable {

    private String title, format, director, distributor;
    private int  year, duration;

    private Film[] filmsArray;

    //@Inject
    private Filmclub filmclub;

    public FilmsucheViewController() {
        //filmclub = new Filmclub();
        filmclub = Filmclub.getInstance();
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
            counter++;
        }
        if(!format.equals("-")){
            counter++;
        }
        if(duration > 0){
            counter++;
        }
        if(!director.equals("")){
            counter++;
        }
        if(!distributor.equals("")){
            counter++;
        }
        if(year > 0){
            counter++;
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
            filmclub.search(title, director, distributor, format, duration, year);
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/filmliste.xhtml");
        }
        //todo print error to add values
    }

    @PostConstruct
    public void init(){
        //filmclub.initialise();
    }
}
