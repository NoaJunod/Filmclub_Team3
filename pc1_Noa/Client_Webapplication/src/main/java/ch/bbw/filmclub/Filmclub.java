package ch.bbw.filmclub;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ch.bbw.film.Film;

import java.util.ArrayList;

/**
 *
 * @author Laura Lüthi
 */
public class Filmclub {

    private ArrayList<Film> filme;

    public Filmclub() {
        filme = new ArrayList<>();
    }

    public ArrayList<Film> getFilme() {
        return filme;
    }

    public void setFilme(ArrayList<Film> filme) {
        this.filme = filme;
    }
}
