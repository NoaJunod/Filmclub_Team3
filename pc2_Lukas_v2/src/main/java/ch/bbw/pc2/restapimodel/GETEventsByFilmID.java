/*
  Author: Lukas Meili
 */
package ch.bbw.pc2.restapimodel;

import ch.bbw.pc2.model.db.DBSession;
import ch.bbw.pc2.model.db.Film;
import ch.bbw.pc2.model.db.Presentation;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * This class handles the get request for {ip}/film/events_by_film_id.
 */
public class GETEventsByFilmID {
    /**
     * This method queries through the database to find presentation entries which match to the film id.
     * FilmID parameter is mandatory.
     * @param filmID
     * @return
     */
    public static JSONObject getEventsByFilmID(int filmID) {
        JSONObject answer = new JSONObject();
        Session session = null;

        try {
            session = DBSession.getSession();

            //load film from db
            Film f = new Film();
            session.load(f, filmID);
            JSONObject filmObject = new JSONObject();
            filmObject.put("Id", f.getFilmId());
            filmObject.put("title", f.getTitle());
            filmObject.put("format", f.getFormat());
            filmObject.put("director", f.getDirector());
            filmObject.put("duration", f.getDuration());
            filmObject.put("yearOfProduction", f.getYearOfProduction());
            filmObject.put("distributor", f.getDistributor());
            answer.put("film", filmObject);


            //load presentations from db
            List<Presentation> presentations = session.createQuery("FROM presentation where film_idfs like " + filmID).list();

            //format in json(array)
            JSONArray presentationArray = new JSONArray();
            for(Presentation p:presentations){
                JSONObject tmpJsonObject = new JSONObject();
                tmpJsonObject.put("date", p.getDate());
                tmpJsonObject.put("room", p.getRoomByRoomIdfs().getName());
                tmpJsonObject.put("time", p.getTime().toString());

                presentationArray.add(tmpJsonObject);
            }
            answer.put("presentations", presentationArray);

            return answer;
        } catch (Exception e){
            answer = new JSONObject();
            answer.put("ERROR", "Something has gone wrong");
            answer.put("ERRORMSG", e.getMessage());
            return answer;
        } finally {
            session.close();
        }
    }
}