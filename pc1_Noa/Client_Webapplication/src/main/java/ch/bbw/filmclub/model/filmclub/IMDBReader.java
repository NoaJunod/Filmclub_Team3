package ch.bbw.filmclub.model.filmclub;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author 5im16nivanderheide
 */
public class IMDBReader {

    private String urlSearch;
    private String urlMovie;
    private String title;
    Document docSearch;
    Document docMovie;

    /**
     * Constructor
     * @param title
     * @throws IOException 
     */
    public IMDBReader(String title) throws IOException {
            title = title.toLowerCase();
            this.urlSearch = "https://www.imdb.com/find?ref_=nv_sr_fn&q=" + title + "&s=tt";
            this.title = title;
            this.docSearch = Jsoup.connect(urlSearch).get();
    }
    
    /**
     * checks if IMDB finds a Film for a given input
     */
    public boolean movieExists() {
        Elements movies = docSearch.select("a");
        for (Element movie : movies) {
            String href = movie.absUrl("href");
            if (href.contains("/title/tt")) {
                if (!movie.hasClass("fallback")) {
                    String text = movie.text().toLowerCase();
                    if (title.contains(text)) {
                        urlMovie = href;
                        try {
                            this.docMovie = Jsoup.connect(urlMovie).get();
                        } catch (IOException ex) {
                            Logger.getLogger(IMDBReader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * returns the link of the top search result
     */
    public String getLinkOfTitle() {
        System.out.println(urlMovie);
        if(movieExists()){
            return urlMovie;
        }
        return "https://www.youtube.com/user/PewDiePie";
    }

    /**
     * returns the director of the top search result
     */
    public String getDirectorOfTitle() {
        Elements elements = docMovie.select("div");
        for (Element element : elements) {
            if (element.hasClass("credit_summary_item")) {
                Elements directors = element.select("a");
                for (Element director : directors) {
                    String name = director.text();
                    return name;
                }
            }
        }
        return null;
    }

    /**
     * returns the durration of the top search result
     */
    public int getDurationOfTitle() {
        Elements elements = docMovie.select("time");
        for (Element element : elements) {
            if (element.hasAttr("datetime")) {
                String timeString = element.text();
                String[] timeArray = timeString.split(" ");
                String hours = timeArray[0].replace("h", "");
                String minutes = timeArray[1].replace("min", "");
                int duration = (Integer.parseInt(hours) * 60) + Integer.parseInt(minutes);
                return duration;
            }
        }
        return 0;
    }

    /**
     * returns the year of production of the top search result
     */
    public int getYearOfTitle() {
        Elements elements = docMovie.select("div");
        for (Element element : elements) {
            if (element.hasClass("subtext")) {
                Elements years = element.select("a");
                for (Element year : years) {
                    if (year.absUrl("href").contains("/title/tt")) {
                        String timeString = year.text();
                        String[] timeArray = timeString.split(" ");
                        String yearString = timeArray[2];
                        int yearValue = Integer.parseInt(yearString);
                        return yearValue;
                    }
                }
            }
        }
        return 0;
    }
}
