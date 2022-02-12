package fr.orsys.fx.kanban.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.orsys.fx.kanban.business.Ville;

public interface VilleService {

	List<Ville> importerVilles() throws IOException, SQLException;

	Ville ajouterVille(Ville ville);

	Ville recupererVille(Long id);

	List<Ville> recupererVilles();

}
