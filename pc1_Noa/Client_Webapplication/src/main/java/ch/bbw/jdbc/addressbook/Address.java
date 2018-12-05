/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.jdbc.addressbook;

/**
 *
 * @author 5im16beglaus
 */
public class Address {
   
    private int id;
    private String firstname;
    private String nachname;
    private String number;

    public Address(int id, String name, String nachname, String number) {
        this.id = id;
        this.firstname = name;
        this.nachname = nachname;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return firstname;
    }

    public void setName(String name) {
        this.firstname = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
    
    
}
