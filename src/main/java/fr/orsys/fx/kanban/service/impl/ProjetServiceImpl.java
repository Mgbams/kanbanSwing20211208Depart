package fr.orsys.fx.kanban.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.orsys.fx.kanban.business.Projet;

public class ProjetServiceImpl implements fr.orsys.fx.kanban.service.ProjetService {
	List<Projet> projets = new ArrayList<>();
	
	@Override
	public Projet ajouterProjet(Projet projet) {
		projets.add(projet);
		return projet;
	}

	@Override
	public Projet recupererProjet(Long id) {
		for (Projet projet: projets) {
			if (projet.getId().equals(id)) {
				return projet;
			}
		}
		
		return null;
	}

	@Override
	public List<Projet> recupererProjets() {
		return projets;
	}

	@Override
	public boolean supprimerProjet(Long id) {
		Projet projetASupprimer = recupererProjet(id);
		if (projetASupprimer == null) {
			return false;
		} else {
			return projets.remove(projetASupprimer);
		}
	}

}
