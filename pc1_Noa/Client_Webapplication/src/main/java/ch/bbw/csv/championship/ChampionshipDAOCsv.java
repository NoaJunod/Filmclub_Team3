package ch.bbw.csv.championship;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ChampionshipDAOCsv implements ChampionshipDAO {
    private String file = "D:\\Dokumente\\01_BBW\\02_Module\\M151\\Unterlagen\\03_Datenformate\\03_1_CSV\\realtime.csv";

    public ChampionshipDAOCsv() {
    }

    @PostConstruct
    private void init() {
        //add driver
        //create connection
    }

    @PreDestroy
    private void destroy() {
        //close connection
    }

    @Override
    public void create(Championship championship) {
        //create, not implemented yet
    }

    @Override
    public Championship read(int id) {
        // read, not implemented yet
        return null;
    }

    @Override
    public List<Championship> readAll() {
        List<Championship> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String line;

            while ((line = reader.readLine()) != null) {  // while not eof
                line = line.replace('\"', ' ');
                String[] tokens = line.split(";");
                // tokens[8] entpricht dem competition date
                Date date = format.parse(tokens[8]);
                Championship championship = new Championship(date, tokens[6], tokens[tokens.length - 1]);
                list.add(championship);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void update(Championship championship) {
        // update, not implemented yet
    }

    @Override
    public void delete(int id) {
        // delete, not implemented yet
    }

}
