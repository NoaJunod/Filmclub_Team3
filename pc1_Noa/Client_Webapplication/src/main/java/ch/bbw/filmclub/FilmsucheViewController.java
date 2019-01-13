package ch.bbw.filmclub;


import com.sun.javafx.fxml.builder.URLBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

@Named
@RequestScoped
public class FilmsucheViewController {

    private String title, format, director, distributor;
    private int  year, duration;
    private Filmclub filmclub;
    private ArrayList<String> searches;

    public FilmsucheViewController() {
        filmclub = new Filmclub();
        searches = new ArrayList<>();
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
            searches.add(title);
            counter++;
        } else {
            searches.add("");
        }
        if(!format.equals("-")){
            searches.add(format);
            counter++;
        } else {
            searches.add("");
        }
        if(duration > 0){
            searches.add(duration + "");
            counter++;
        } else {
            searches.add("");
        }
        if(!director.equals("")){
            searches.add(director);
            counter++;
        } else {
            searches.add("");
        }
        if(!distributor.equals("")){
            searches.add(distributor);
            counter++;
        } else {
            searches.add("");
        }
        if(year > 0){
            searches.add(year + "");
            counter++;
        } else {
            searches.add("");
        }
        if(counter >= 2){
            return true;
        } else {
            return false;
        }
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
            String output;

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        }
    }
}
