package ch.bbw.filmclub;


import ch.bbw.film.Film;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FilmHinzufuegenViewController {

    private String title, director;

    public FilmHinzufuegenViewController() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void add() throws IOException {
        //title.trim();
        //director.trim();
        //String params = "title=" + "Bible421" + "&format=35 mm&director=" + "Sam" + "&yearOfProduction=0&duration=69&distributor=Frank&director=Frank2";

        Map<String, Object> params = new HashMap<>();
        params.put("title", "BItsch UHH NO I MEANT BIBLE");
        params.put("format", "420 mm");
        params.put("director", "FrankTheDank");
        params.put("yearOfProduction", 1);
        params.put("duration", 69);
        params.put("distributor", "Lukases Arts");

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
        StringBuilder json = new StringBuilder();
        String receivedLine;
        while ((receivedLine = br.readLine()) != null)
            json.append(receivedLine);

        JSONObject jsonObject = new JSONObject(json.toString());
        System.out.println(jsonObject.toString());

        /*
        "BAD"
        while ((output = br.readLine()) != null) {
            System.out.println(output);


            JSONParser parser = new JSONParser();
            try {
                Object root = parser.parse(output);
                org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) root;
                JSONArray filmsJSON = (JSONArray) jsonObject.get("films");
                System.out.println(films.toString());
                Gson gson = new Gson();
                if(filmsJSON.size() > 0) {
                    filmsArray = gson.fromJson(filmsJSON.toString(), Film[].class);
                    System.out.println(filmsArray[0].getTitle());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/
        conn.disconnect();
    }

    public static void main(String[] args) throws IOException {
        FilmHinzufuegenViewController filmHinzufuegenViewController = new FilmHinzufuegenViewController();
        filmHinzufuegenViewController.add();
    }
}
