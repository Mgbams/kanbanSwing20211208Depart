package fr.orsys.fx.kanban.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.orsys.fx.kanban.business.Developpeur;

public class DeveloppeurServiceImpl implements fr.orsys.fx.kanban.service.DeveloppeurService {
	List<Developpeur> developpeurs = new ArrayList<>();
	
	@Override
	public Developpeur ajouterDeveloppeur(Developpeur developpeur) {
		developpeurs.add(developpeur);
		return developpeur;
	}

	@Override
	public Developpeur recupererDeveloppeur(Long id) {
		for (Developpeur developpeur: developpeurs) {
			if (developpeur.getId().equals(id)) {
				return developpeur;
			}
		}
		
		return null;
	}

	@Override
	public List<Developpeur> recupererDeveloppeurs() {
		return developpeurs;
	}

	@Override
	public boolean supprimerDeveloppeur(Long id) {
		Developpeur developpeurASupprimer = recupererDeveloppeur(id);
		
		if (developpeurASupprimer == null) {
			return false;
		} else {
			return developpeurs.remove(developpeurASupprimer);
		}
	}

}
