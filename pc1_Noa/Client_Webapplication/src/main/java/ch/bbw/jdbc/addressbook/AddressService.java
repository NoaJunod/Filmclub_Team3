/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.jdbc.addressbook;

import ch.bbw.csv.championship.Championship;
import ch.bbw.csv.championship.ChampionshipDAOCsv;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 5im16beglaus
 */
@Named
@ApplicationScoped
public class AddressService implements Serializable {

    @Inject
    private AddressDAOImpl addressDAO;

    public List<Address> getAllAddress() {
        return addressDAO.readAll();
    }

}
