package ch.bbw.filmclub;

import java.io.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Noa Junod
 */

@Named
@RequestScoped
public class FilmHinzufuegenViewController {

    private String title, format, director, distributor;
    private int  year, duration;

    @Inject
    private Filmclub filmclub;

    public FilmHinzufuegenViewController() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

    public void add() throws IOException {
        filmclub.add(title, format, director, year, duration, distributor);
    }

    @PostConstruct
    public void init(){
        filmclub.initialise();
    }
}
