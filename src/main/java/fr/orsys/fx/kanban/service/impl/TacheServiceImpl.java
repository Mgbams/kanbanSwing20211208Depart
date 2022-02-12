package fr.orsys.fx.kanban.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.orsys.fx.kanban.business.Developpeur;
import fr.orsys.fx.kanban.business.Projet;
import fr.orsys.fx.kanban.business.Tache;
import fr.orsys.fx.kanban.business.TypeTache;
import fr.orsys.fx.kanban.service.TacheService;

public class TacheServiceImpl implements TacheService {
	List<Tache> taches = new ArrayList<>();

	@Override
	public Tache ajouterTache(Developpeur developpeur, String intitule, int nbHeuresEstimees, Projet projet,
			TypeTache typeTache) {
		Tache nouveauTache = new Tache(developpeur, intitule, nbHeuresEstimees, projet, typeTache );
		return nouveauTache;
	}

	@Override
	public Tache ajouterTache(Tache tache) {
		taches.add(tache);
		return tache;
	}

	@Override
	public Tache recupererTache(Long id) {
		for (Tache tache: taches) {
			if (tache.getId().equals(id)) {
				return tache;
			}
		}
		
		return null;
	}

	@Override
	public List<Tache> recupererTaches() {
		return taches;
	}

	@Override
	public boolean supprimerTache(Long id) {
		Tache tacheASupprimer =  recupererTache(id);
		if (tacheASupprimer == null) {
			return false;
		} else {
			return taches.remove(tacheASupprimer);
		}
	}

}
