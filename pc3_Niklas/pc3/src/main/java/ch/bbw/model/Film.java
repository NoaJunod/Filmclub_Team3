/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.model;

/**
 *
 * @author 5im16nivanderheide
 */
public class Film {
    
    private int id, year_of_production, duration;
    private String title, format, director, distributor;

    public Film(int id, int year_of_production, int duration, String title, String format, String director, String distributor) {
        this.id = id;
        this.year_of_production = year_of_production;
        this.duration = duration;
        this.title = title;
        this.format = format;
        this.director = director;
        this.distributor = distributor;
    }

    public int getId() {
        return id;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getFormat() {
        return format;
    }

    public String getDirector() {
        return director;
    }

    public String getDistributor() {
        return distributor;
    }
    
    
    
}
