package ch.bbw.filmclub;


import ch.bbw.film.Film;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class FilmsucheViewController {

    private String title, format, director, distributor;
    private int  year, duration;

    private Film[] filmsArray;

    private ArrayList<Film> films;

    public FilmsucheViewController() {
        films = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean checkIfFilledIn(){
        int counter = 0;
        if(!title.equals("")){
            counter++;
        }
        if(!format.equals("-")){
            counter++;
        }
        if(duration > 0){
            counter++;
        }
        if(!director.equals("")){
            counter++;
        }
        if(!distributor.equals("")){
            counter++;
        }
        if(year > 0){
            counter++;
        }
        return counter >= 2;
    }

    public Film[] getFilmsArray() {
        return filmsArray;
    }

    public void setFilmsArray(Film[] filmsArray) {
        this.filmsArray = filmsArray;
    }

    public void search() throws IOException {
        if(checkIfFilledIn()) {
            String params = "?";
            if(!title.equals("")){
                params += "title" + "=" + title + "&";
            }
            if(!director.equals("")){
                params += "director" + "=" + director + "&";
            }
            if(!distributor.equals("")){
                params += "distributor" + "=" + distributor + "&";
            }
            if(!format.equals("-")){
                params += "format" + "=" + format + "&";
            }
            if(duration != 0){
                params += "duration" + "=" + duration + "&";
            }
            if(year != 0){
                params += "yearOfProduction" + "=" + year + "&";
            }
            URL url = new URL("http://yeet.onthewifi.com:8080/film/query" + params);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            System.out.println(params);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            StringBuilder jsonString = new StringBuilder();
            String receivedLine;
            while ((receivedLine = br.readLine()) != null)
                jsonString.append(receivedLine);

            System.out.println("Response from Server .... \n");
            System.out.println(jsonString.toString());

            Gson gson = new Gson();
            JsonObject reasonsJson = gson.fromJson(jsonString.toString(), JsonObject.class);
            JsonArray reasonsFilmsArray = reasonsJson.getAsJsonArray("films");
            films = gson.fromJson(reasonsFilmsArray, new TypeToken<List<Film>>(){}.getType());

            /*
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                    Gson gson = new Gson();
                    if(output.length() > 0) {
                        filmsArray = gson.fromJson(output, Film[].class);
                        System.out.println(filmsArray[0].getTitle());
                    }
            }*/

            conn.disconnect();
        }
    }

    //todo delete
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://yeet.onthewifi.com:8080/film/query");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));


        StringBuilder jsonString = new StringBuilder();
        String receivedLine;
        while ((receivedLine = br.readLine()) != null)
            jsonString.append(receivedLine);

        System.out.println("Response from Server .... \n");
        System.out.println(jsonString.toString());

        Gson gson = new Gson();
        JsonObject reasonsJson = gson.fromJson(jsonString.toString(), JsonObject.class);
        JsonArray reasonsFilmsArray = reasonsJson.getAsJsonArray("films");
        List films = gson.fromJson(reasonsFilmsArray, new TypeToken<List<Film>>(){}.getType());
        System.out.println(films);
    }
}
