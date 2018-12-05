package ch.bbw.csv.championship;


import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ChampionshipService implements Serializable {

	@Inject
    private ChampionshipDAOCsv championshipDAO;

    public List<Championship> getAllChampionships() {
        return championshipDAO.readAll();
    }

}
