package ch.bbw.pc2.restapimodel;

import ch.bbw.pc2.model.db.DBSession;
import ch.bbw.pc2.model.db.Film;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

public class GETFilm {
    public static JSONObject getFilms(String title, String format, String director, int yearOfProduction, int duration, String distributor) {
        JSONObject answer = new JSONObject();
        Session session = null;
        try {
            session = DBSession.getSession();

            String query = "FROM Film where " +
                    "title like '%" + Objects.toString(title, "") + "%'" +
                    " and  format like '%" + Objects.toString(format, "") + "%'" +
                    " and director like '%" + Objects.toString(director, "") + "%'" +
                    " and distributor like '%" + Objects.toString(distributor, "") + "%'";

            if (yearOfProduction != 0)
                query += " and yearOfProduction like " + yearOfProduction;

            if (duration != 0)
                query += " and duration like " + duration;

            List<Film> films = session.createQuery(query).list();

            session.close();

            JSONArray filmJsonArray = new JSONArray();

            for (Film f : films) {
                JSONObject tempJSON = new JSONObject();
                tempJSON.put("Id", f.getFilmId());
                tempJSON.put("title", f.getTitle());
                tempJSON.put("format", f.getFormat());
                tempJSON.put("director", f.getDirector());
                tempJSON.put("duration", f.getDuration());
                tempJSON.put("yearOfProduction", f.getYearOfProduction());
                tempJSON.put("distributor", f.getDistributor());
                filmJsonArray.add(tempJSON);
            }

            answer.put("films", filmJsonArray);
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
