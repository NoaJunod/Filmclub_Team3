/*
 * Author: Lukas Meili
 */
package ch.bbw.filmclub.model.veranstaltung;

import ch.bbw.film.Film;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * This class manages the request to the server for presentations.
 * Can be static because no variables have to be stored
 */
public class VeranstaltungenManager {

    /**
     * Get all presentations from one film
     * @param id
     * @return
     */
    public static FilmVeranstaltungResponse getVeranstalungenFromApi(long id) {
        URL url;
        try {
            //url with parameter
            url = new URL("http://172.25.22.33:8080/film/events_by_film_id?filmID=" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("URL is probably wrong!");
            return null;
        }

        System.out.println("Request to: " + url.toString());
        HttpURLConnection conn = null;
        try {
            //connect to the server via https
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder jsonString = new StringBuilder();
            String receivedLine;
            while ((receivedLine = br.readLine()) != null)
                jsonString.append(receivedLine);

            System.out.println("Response from Server ....n");
            System.out.println(jsonString.toString());

            //transform the response in local class-objects
            Gson gson = new Gson();
            JsonObject responseJson = gson.fromJson(jsonString.toString(), JsonObject.class);

            //an instance of the film is also given, so we can use this one.
            //transform film
            JsonObject filmJson = responseJson.getAsJsonObject("film");
            Film film = gson.fromJson(filmJson, Film.class);

            //transform
            JsonArray veranstaltungenJsonArray = responseJson.getAsJsonArray("presentations");
            List<Veranstaltung> veranstaltungen = gson.fromJson(veranstaltungenJsonArray, new TypeToken<List<Veranstaltung>>() {}.getType());

            //should return film and presentations, so we need a new object
            return new FilmVeranstaltungResponse(film, veranstaltungen);
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                //must be closed
                conn.disconnect();
            } catch (NullPointerException npex) {
            }
        }
    }
}
