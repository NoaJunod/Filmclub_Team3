/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.jdbc.addressbook;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 5im16beglaus
 */
@Named
@RequestScoped
public class AddressViewController {

    @Inject
    private AddressService addressService;


    public AddressViewController() {
    }

    public List<Address> getAddresses() {
        return addressService.getAllAddress();
    }

}
