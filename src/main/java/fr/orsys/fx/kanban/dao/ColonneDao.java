package fr.orsys.fx.kanban.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.fx.kanban.business.Colonne;

public interface ColonneDao {
	Colonne create(Colonne colonne) throws SQLException;

	Colonne findOne(Long id) throws SQLException;

	List<Colonne> findAll() throws SQLException;

	List<Colonne> findByNomStartingWith(String nom) throws SQLException;
}
