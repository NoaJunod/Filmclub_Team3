package ch.bbw.pc2.restapimodel;

import ch.bbw.pc2.model.db.DBSession;
import ch.bbw.pc2.model.db.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;


public class PUTFilm {
    public static JSONObject addFilm(String title, String format, String director, int yearOfProduction, int duration, String distributor) {
        JSONObject answer = new JSONObject();
        if(title == null || format == null || director == null || yearOfProduction == 0 || duration == 0|| distributor  == null){
            answer.put("ERROR", "Film was not added to database!");
            answer.put("ERRORMSG", "One of the values was not set.");
            return answer;
        }
        Session session = null;
        try {
            Film film = new Film();
            film.setTitle(title);
            film.setFormat(format);
            film.setDirector(director);
            film.setYearOfProduction(yearOfProduction);
            film.setDuration(duration);
            film.setDistributor(distributor);

            session = DBSession.getSession();
            Transaction tx = session.beginTransaction();
            session.save(film);
            tx.commit();

            answer.put("SUCCESS", "The film was successfully added to the database!");
            return answer;
        }catch (Exception e){
            answer.put("ERROR", "Something has gone wrong");
            answer.put("ERRORMSG", e.getMessage());
            return answer;
        } finally {
            session.close();
        }
    }
}