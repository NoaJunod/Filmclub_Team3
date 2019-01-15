package ch.bbw.filmclub;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ch.bbw.film.Film;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noa Junod
 */
@Named
@SessionScoped
public class Filmclub implements Serializable {

    private static final long serialVersionUID = 1;
    private boolean isInitialised;
    private ArrayList<Film> films;

    public Filmclub() {
        isInitialised = false;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public void search(String params) throws IOException {
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

    public void add(){

    }

    public void initialise(){
        if(!isInitialised)  {
            isInitialised = true;
            films = new ArrayList<>();
        }
    }
}
