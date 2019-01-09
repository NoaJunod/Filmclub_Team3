/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.rmi;

import ch.bbw.film.Film;
import ch.bbw.model.FilmListWriter;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author 5im16nivanderheide
 */
public class FilmListImpl extends UnicastRemoteObject implements FilmList {

    public FilmListImpl() throws RemoteException {
    }

    public void time() {
        LocalDateTime now = LocalDateTime.now();
        System.out.print(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(now)+": ");
    }

    @Override
    public String getResponse() throws RemoteException{
        time();
        System.out.println("check recieved");
        return "working";
    }

    @Override
    public void exportFilmList(ArrayList<Film> filmlist) throws RemoteException{
        time();
        System.out.println("list recieved");        
        new FilmListWriter().writeFilmList(filmlist);
    }

}
