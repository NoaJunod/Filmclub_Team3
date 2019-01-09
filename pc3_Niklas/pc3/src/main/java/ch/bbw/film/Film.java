package ch.bbw.film;

import java.io.Serializable;

public class Film implements Serializable {

    private int id;
    private String title;
    private String format;
    private String director;
    private int yearOfProduction;
    private int duration;
    private String distributor;

    public Film(int id, String title, String format, String director, int yearOfProduction, int duration, String distributor) {
        this.id = id;
        this.title = title;
        this.format = format;
        this.director = director;
        this.yearOfProduction = yearOfProduction;
        this.duration = duration;
        this.distributor = distributor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
}