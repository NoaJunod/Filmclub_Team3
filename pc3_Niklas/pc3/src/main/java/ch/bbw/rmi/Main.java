/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.rmi;

import java.net.Inet4Address;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author 5im16nivanderheide
 */
public class Main {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            System.out.println("Could not start Registry");
        }
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            System.out.println("address: "+hostAddress);
            System.setProperty("java.rmi.server.hostname", hostAddress);
            FilmListImpl impl = new FilmListImpl();
            String motdService = "xml";
            Naming.rebind("//" + hostAddress + "/" + motdService, impl);
            System.out.println("started");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
