/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.model;

import ch.bbw.model.noa.IMDBReader;
import java.util.ArrayList;

/**
 *
 * @author 5im16nivanderheide
 */
public class Main {
    
    public static void main(String[] args) {
        /*
        Film f1 = new Film(1, 2000, 200, "The battle of PewDiePie vs T-Series", "mp4", "YouTube.com", "THE INTERNET");
        Film f2 = new Film(2, 20010, 200, "The end", "mp4", "heyyy.com", "THE DISTRIBUTOR");
        Film f3 = new Film(1, 2000, 200, "The battle of PewDiePie vs T-Series", "mp4", "YouTube.com", "THE INTERNET333333333");
        Film f4 = new Film(2, 20010, 200, "The end", "mp4", "heyyy.com", "THE DISTRIBUTOR4444444444");
        ArrayList<Film> filmList = new ArrayList<>();
        filmList.add(f1);
        filmList.add(f2);
        filmList.add(f3);
        filmList.add(f4);
        new FilmListWriter().writeFilmList(filmList);
        System.out.println("done!");
                */
        
        String movie = "Harry Potter";
        IMDBReader ir = new IMDBReader(movie);
        System.out.println("-----------------------"+movie+"-----------------------");
        if(ir.movieExists()){
            System.out.println(ir.getLinkOfTitle());
            System.out.println("movie exists");    
            System.out.println("Director: " + ir.getDirectorOfTitle());
            System.out.println("Duration: " + ir.getDurationOfTitle()+ " min");
            System.out.println("Year: " + ir.getYearOfTitle());
        }else{
            System.out.println("no");
        }
        
        System.out.println("done!");
    }
   
}
