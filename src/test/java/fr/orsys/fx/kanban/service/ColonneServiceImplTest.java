package fr.orsys.fx.kanban.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import fr.orsys.fx.kanban.business.Colonne;
import fr.orsys.fx.kanban.service.impl.ColonneServiceImpl;

class ColonneServiceImplTest {
	private ColonneService colonneService = new ColonneServiceImpl();

	@Test
	@Order(1)
	void testAjouterColonne() {
		String nomColonne = "Test";
		Colonne colonne = colonneService.ajouterColonne(nomColonne);
		assertNotNull(colonne);
		assertNotNull(colonne.getNom());
		assertEquals(colonne.getNom(), nomColonne);
	}

	@Test
	@Order(2)
	void testRecupererColonne() {
		String nomColonne = "Test";
		Colonne colonne = colonneService.ajouterColonne(nomColonne);
		colonne = colonneService.recupererColonne(colonne.getId());
		assertNotNull(colonne);
		assertEquals(colonne.getNom(), nomColonne);
		assertTrue(colonne.getId() > 0);
	}

	@Test
	@Order(3)
	void testRecupererColonnes() {
		String nomColonne = "Test";
		Colonne colonne = colonneService.ajouterColonne(nomColonne);
		List<Colonne> colonnes = new ArrayList<>();
		colonnes = colonneService.recupererColonnes();
		assertNotNull(colonnes);
		assertTrue(colonnes.size() > 0);
		
	}

}
