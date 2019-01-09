/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.rmi;


import ch.bbw.model.Film;
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
    public ArrayList<Film> getFilmList() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
