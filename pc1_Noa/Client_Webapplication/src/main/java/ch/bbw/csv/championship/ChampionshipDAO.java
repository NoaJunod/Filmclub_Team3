package ch.bbw.csv.championship;

import java.util.List;

public interface ChampionshipDAO {
	void create(Championship champioship);

	Championship read(int id);

	List<Championship> readAll();

	void update(Championship champioship);

	void delete(int id);
}
