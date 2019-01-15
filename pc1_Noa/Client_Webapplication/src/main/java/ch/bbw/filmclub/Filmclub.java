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

    private static final long serialVersionUID = 1;
    private static boolean isInitialised;
    private ArrayList<Film> films;

    private String title, format, director, distributor;
    private int  year, duration;

    public Filmclub() {
        isInitialised = false;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
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

    /**
     * Suche auf dem Server nach den Filmen, die den Suchkriterien entsprechen
     * @throws IOException
     */
    public void search() throws IOException {

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
        if(!format.equals("")){
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

        conn.disconnect();
    }

    /**
     * Film in die Datenbank hinzufügen
     * @throws IOException
     */
    public void add(String title, String format, String director, int year, int duration, String distributor) throws IOException {
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
            url = new URL("http://yeet.onthewifi.com:8080/film/add");
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

        JSONObject jsonObject = new JSONObject(jsonString.toString());
        System.out.println(jsonObject.toString());

        conn.disconnect();
    }

    public void initialise(){
        if(!isInitialised)  {
            isInitialised = true;
        }
    }
}
