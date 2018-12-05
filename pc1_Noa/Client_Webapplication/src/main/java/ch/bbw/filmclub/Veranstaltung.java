package ch.bbw.filmclub;

import java.util.Date;

public class Veranstaltung {

    private Date datum;
    private String zeit;
    private Raum raum;

    public Veranstaltung(Date datum, String zeit, Raum raum) {
        this.datum = datum;
        this.zeit = zeit;
        this.raum = raum;
    }
}
