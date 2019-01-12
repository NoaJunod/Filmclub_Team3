/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.model;

import ch.bbw.film.Film;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author 5im16nivanderheide
 */
public class FilmListWriter {

    public FilmListWriter() {
        
    }

    public void writeFilmList(ArrayList<Film> filmList) {
        try {
            Element root = new Element("filmList");
            Namespace n = Namespace.getNamespace("xsi", "src/main/resources/xml/FilmListSchema.xsd");
            root.setAttribute("noNameSpaceSchemaLocation", "FilmListSchema.xml", n);
            
            Element filmElement;
            for (Film f : filmList) {
                filmElement = new Element("filmElement");
                filmElement.addContent(new Element("id").addContent(f.getId() + ""));
                filmElement.addContent(new Element("title").addContent(f.getTitle() + ""));
                filmElement.addContent(new Element("format").addContent(f.getFormat() + ""));
                filmElement.addContent(new Element("director").addContent(f.getDirector() + ""));
                filmElement.addContent(new Element("year_of_production").addContent(f.getYearOfProduction()+ ""));
                filmElement.addContent(new Element("duration").addContent(f.getDuration() + ""));
                filmElement.addContent(new Element("distributor").addContent(f.getDistributor() + ""));
                root.addContent(filmElement);
            }
            
            XMLOutputter outputter = new XMLOutputter();
            FileOutputStream output;
            Document dokument = new Document(root);
            output = new FileOutputStream("src/main/resources/xml/FilmList.xml");
            outputter.output(dokument, output);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilmListWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilmListWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
