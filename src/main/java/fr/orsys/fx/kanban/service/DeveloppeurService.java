package fr.orsys.fx.kanban.service;

import java.util.List;

import fr.orsys.fx.kanban.business.Developpeur;

public interface DeveloppeurService {
	Developpeur ajouterDeveloppeur(Developpeur developpeur);

	Developpeur recupererDeveloppeur(Long id);

	List<Developpeur> recupererDeveloppeurs();

	boolean supprimerDeveloppeur(Long id);
}
