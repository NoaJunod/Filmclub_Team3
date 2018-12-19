package ch.bbw.filmclub;


import org.json.JSONObject;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class FilmclubViewController {

    private String textJSON;


    public FilmclubViewController() {
        JSONObject jsonObject = new JSONObject("resources/searchCriteria.json");
        textJSON = jsonObject.getString("title");
    }

    public String getTextJSON() {
        JSONObject jsonObject = new JSONObject("resources/searchCriteria.json");
        textJSON = jsonObject.getString("title");

        return textJSON;
    }

    public void setTextJSON(String textJSON) {
        this.textJSON = textJSON;
    }
}
