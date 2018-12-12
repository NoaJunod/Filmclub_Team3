package ch.bbw.filmclub;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.FileWriter;
import java.io.IOException;
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
            searches.add(null);
        }
        if(!format.equals("")){
            searches.add(format);
            counter++;
        } else {
            searches.add(null);
        }
        if(duration > 0){
            searches.add(duration + "");
            counter++;
        } else {
            searches.add(null);
        }
        if(!director.equals("")){
            searches.add(director);
            counter++;
        } else {
            searches.add(null);
        }
        if(!distributor.equals("")){
            searches.add(distributor);
            counter++;
        } else {
            searches.add(null);
        }
        if(year > 0){
            searches.add(year + "");
            counter++;
        } else {
            searches.add(null);
        }
        if(counter >= 2){
            return true;
        } else {
            return false;
        }
    }

    public void search() throws IOException {
        if(checkIfFilledIn()){
            JSONObject root = new JSONObject();
            root.put("title", searches.get(0));
            root.put("format", searches.get(1));
            root.put("director", searches.get(3));
            root.put("year_of_production", searches.get(5));
            root.put("duration", searches.get(2));
            root.put("distributor", searches.get(4));

            FileWriter file = new FileWriter("D:\\M151\\Projektarbeit\\Filmclub\\pc1_Noa\\Client_Webapplication\\src\\main\\webapp\\resources\\searchCriteria.json");
                file.write(root.toString());
                System.out.println("Successfully Copied JSON Object to File...");
                System.out.println("\nJSON Object: " + root);
                file.flush();
                file.close();
        } else {
            searches.clear();
        }
    }
}
