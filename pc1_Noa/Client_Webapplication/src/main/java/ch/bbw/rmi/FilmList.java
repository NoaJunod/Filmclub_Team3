/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.rmi;

import ch.bbw.film.Film;

import java.lang.reflect.UndeclaredThrowableException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author 5im16nojunod
 */
public interface FilmList extends Remote{
    
    public String getResponse() throws RemoteException;
    
    public void exportFilmList(ArrayList<Film> filmlist) ;
}
