/*
 * Author: Lukas Meili
 */
package ch.bbw.filmclub.model.veranstaltung;

import ch.bbw.filmclub.Raum;

import java.util.Date;

public class Veranstaltung {

    private String time,room,date;

    public Veranstaltung(String time, String room, String date) {
        this.time = time;
        this.room = room;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
