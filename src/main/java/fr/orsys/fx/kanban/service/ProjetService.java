package fr.orsys.fx.kanban.service;

import java.util.List;

import fr.orsys.fx.kanban.business.Projet;

public interface ProjetService {
	Projet ajouterProjet(Projet projet);

	Projet recupererProjet(Long id);

	List<Projet> recupererProjets();

	boolean supprimerProjet(Long id);
}
