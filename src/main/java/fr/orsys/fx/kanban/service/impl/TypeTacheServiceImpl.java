package fr.orsys.fx.kanban.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.orsys.fx.kanban.business.TypeTache;
import fr.orsys.fx.kanban.service.TypeTacheService;

public class TypeTacheServiceImpl implements TypeTacheService {
	List<TypeTache> typeTaches = new ArrayList<>();
	
	@Override
	public TypeTache ajouterTypeTache(String nom, String couleur) {
		TypeTache nouvelTache = new TypeTache(nom, couleur);
		return nouvelTache;
	}
	
	@Override
	public TypeTache ajouterTypeTache(TypeTache typeTache) {
		typeTaches.add(typeTache);
		return typeTache;
	}

	@Override
	public TypeTache recupererTypeTache(Long id) {
		for (TypeTache typeTache: typeTaches) {
			if (typeTache.getId().equals(id)) {
				return typeTache;
			}
		}
		
		return null;
	}

	@Override
	public List<TypeTache> recupererTypeTaches() {
		return typeTaches;
	}

	@Override
	public boolean supprimerTypeTache(Long id) {
		TypeTache typeTacheASupprimer =  recupererTypeTache(id);
		if (typeTacheASupprimer == null) {
			return false;
		} else {
			return typeTaches.remove(typeTacheASupprimer);
		}
	}

	

}
