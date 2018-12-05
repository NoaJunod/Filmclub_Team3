/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.jdbc.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author 5im16beglaus
 */
@Named
@ApplicationScoped
public class AddressDAOImpl implements AddressDAO{
 
      // Die Datenbankverbindung
    private Connection connection;
 
    @PostConstruct
    private void init (){
        try {
            // Treiber-Klasse Laden
            Class.forName("com.mysql.jdbc.Driver");
            // Verbindung aufbauen
            connection = DriverManager.getConnection("jdbc:mysql://localhost/adressen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
 
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    @PreDestroy
    public void destroy() {
	try {
	   
            connection.close();
            
	} catch (SQLException e) {
           e.printStackTrace();
	}
    }
    
    
    
    public List<Address> readAll() {
    List<Address> list = new ArrayList<>();
    try {
	Statement stmt = connection.createStatement();
	String query = "SELECT * FROM address";
	ResultSet rs = stmt.executeQuery(query);

	while (rs.next()) {
		Address address = new Address(rs.getInt("id"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("number"));
                list.add(address);
	}
	rs.close();
 
    } catch (SQLException e) {
	e.printStackTrace();
    }
    return list;
}

    @Override
    public void create(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
