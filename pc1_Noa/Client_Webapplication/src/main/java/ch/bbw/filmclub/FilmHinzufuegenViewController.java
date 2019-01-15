package ch.bbw.filmclub;


import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
        title.trim();
        director.trim();
        String params = "title=" + title + "&format=null&director=" + director + "&yearOfProduction=null&duration=null&distributor=null";

        byte[] postData       = params.getBytes( StandardCharsets.UTF_8 );
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

    }

}
