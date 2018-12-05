package ch.bbw.csv.championship;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ChampionshipViewController {

    @Inject
    private ChampionshipService championshipService;


    public ChampionshipViewController() {
    }

    public List<Championship> getChampionships() {
        return championshipService.getAllChampionships();
    }

}
