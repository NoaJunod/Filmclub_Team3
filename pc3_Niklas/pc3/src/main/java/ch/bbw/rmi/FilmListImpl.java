/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.rmi;


import ch.bbw.model.Film;
import ch.bbw.model.FilmListWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author 5im16nivanderheide
 */
public class FilmListImpl extends UnicastRemoteObject implements FilmList {      

    public FilmListImpl() throws RemoteException {
    }

    @Override
    public String getResponse() throws RemoteException {
        return "working";
    }

    @Override
    public void exportFilmList(ArrayList<Film> filmlist) {
        new FilmListWriter().writeFilmList(filmlist);
    }

}
