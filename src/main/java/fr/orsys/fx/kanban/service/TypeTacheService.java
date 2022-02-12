package fr.orsys.fx.kanban.service;

import java.util.List;

import fr.orsys.fx.kanban.business.TypeTache;

public interface TypeTacheService {
	TypeTache ajouterTypeTache(String nom, String couleur);

	TypeTache ajouterTypeTache(TypeTache typeTache);

	TypeTache recupererTypeTache(Long id);

	List<TypeTache> recupererTypeTaches();

	boolean supprimerTypeTache(Long id);
}
