/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.model.noa;

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

    private String url;

    public IMDBReader() {
    }

    public boolean movieExists(String title) {
        title = title.toLowerCase();
        url = "https://www.imdb.com/find?ref_=nv_sr_fn&q=" + title + "&s=tt";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements movies = doc.select("a");
            for (Element movie : movies) {
                String href = movie.absUrl("href");
                if (href.contains("/title/tt")) {
                    String text = movie.text().toLowerCase();
                    if (text.equals(title)) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(IMDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getLinkOfTitle(String title) {
        title = title.toLowerCase();
        url = "https://www.imdb.com/find?ref_=nv_sr_fn&q=" + title + "&s=tt";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements movies = doc.select("a");
            for (Element movie : movies) {
                String href = movie.absUrl("href");
                if (href.contains("/title/tt")) {
                    String text = movie.text().toLowerCase();
                    if (text.equals(title)) {
                        return href;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(IMDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getDirectorOfTitle(String title) {
        title = title.toLowerCase();
        url = getLinkOfTitle(title);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div");
            for (Element element : elements) {
                if (element.hasClass("credit_summary_item")) {
                    Elements directors = element.select("a");
                    for (Element director : directors) {
                        String name = director.text();
                        return name;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(IMDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getDurationOfTitle(String title) {
        title = title.toLowerCase();
        url = getLinkOfTitle(title);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("time");
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
        } catch (IOException ex) {
            Logger.getLogger(IMDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getYearOfTitle(String title) {
        title = title.toLowerCase();
        url = getLinkOfTitle(title);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div");
            for (Element element : elements) {
                if (element.hasClass("subtext")) {

                    Elements years = element.select("a");

                    for (Element year : years) {

                        if (element.absUrl("href").contains("/title/tt")) {
                            String timeString = year.text();
                            String[] timeArray = timeString.split(" ");
                            String yearString = timeArray[2];
                            int yearValue = Integer.parseInt(yearString);
                            return yearValue;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(IMDBReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
