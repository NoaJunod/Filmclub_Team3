/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.jdbc.addressbook;

import ch.bbw.csv.championship.Championship;
import java.util.List;

/**
 *
 * @author 5im16beglaus
 */
public interface AddressDAO {
	void create(Address address);

	Address read(int id);

	List<Address> readAll();

	void update(Address address);

	void delete(int id);
}
