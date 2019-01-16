package ch.bbw.filmclub.model.filmclub;
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
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 *
 * @author Noa Junod
 */
@Named
@SessionScoped
public class Filmclub implements Serializable {
    private static Filmclub instance;
    private ArrayList<Film> films;

    public Filmclub() {
        instance = this;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    /**
     * Suche auf dem Server nach den Filmen, die den Suchkriterien entsprechen
     * @throws IOException
     */
    public void search(String title, String director, String distributor, String format, int duration, int year) throws IOException {

        String params = "?";

        //nur parameter die gesetzt sind senden
        if(!title.equals("")){
            params += "title" + "=" + title + "&";
        }
        if(!director.equals("")){
            params += "director" + "=" + director + "&";
        }
        if(!distributor.equals("")){
            params += "distributor" + "=" + distributor + "&";
        }
        //is sometime also "-"
        if(!format.equals("-") && !format.equals("")){
            params += "format" + "=" + format + "&";
        }
        if(duration != 0){
            params += "duration" + "=" + duration + "&";
        }
        if(year != 0){
            params += "yearOfProduction" + "=" + year + "&";
        }

        URL url = new URL("http://172.25.22.33:8080/film/query" + params);

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
        JsonObject responseJson = gson.fromJson(jsonString.toString(), JsonObject.class);
        JsonArray reasonsFilmsArray = responseJson.getAsJsonArray("films");
        films = gson.fromJson(reasonsFilmsArray, new TypeToken<List<Film>>(){}.getType());

        conn.disconnect();
    }

    /**
     * Film in die Datenbank hinzufügen
     * @throws IOException
     */
    public JsonObject add(String title, String format, String director, int year, int duration, String distributor) throws IOException {
        //alle parameter senden
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("format", format);
        params.put("director", director);
        params.put("yearOfProduction", year);
        params.put("duration", duration);
        params.put("distributor", distributor);

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,Object> entry : params.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(Objects.toString(entry.getValue()), "UTF-8"));

        byte[] postData       = sj.toString().getBytes("UTF-8");
        int    postDataLength = postData.length;
        URL    url            = null;
        try {
            url = new URL("http://172.25.22.33:8080/film/add");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "PUT" );
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );
        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
            wr.write( postData );
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        StringBuilder jsonString = new StringBuilder();
        String receivedLine;
        while ((receivedLine = br.readLine()) != null)
            jsonString.append(receivedLine);

        JsonObject jsonObject = new Gson().fromJson(jsonString.toString(), JsonObject.class);
        System.out.println(jsonObject.toString());

        conn.disconnect();
        return jsonObject;
    }

    public static Filmclub getInstance() {
        return instance == null ? new Filmclub() : instance;
    }
}
