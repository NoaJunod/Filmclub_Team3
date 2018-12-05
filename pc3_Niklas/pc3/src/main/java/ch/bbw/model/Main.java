/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.model;

import java.util.ArrayList;

/**
 *
 * @author 5im16nivanderheide
 */
public class Main {
    
    public static void main(String[] args) {
        Film f1 = new Film(1, 2000, 200, "The battle of PewDiePie vs T-Series", "mp4", "YouTube.com", "THE INTERNET");
        Film f2 = new Film(2, 20010, 200, "The end", "mp4", "heyyy.com", "THE DISTRIBUTOR");
        ArrayList<Film> filmList = new ArrayList<>();
        filmList.add(f1);
        filmList.add(f2);
        filmList.add(f1);
        new FilmListWriter().writeFilmList(filmList);
        System.out.println("done!");
    }
   
}
