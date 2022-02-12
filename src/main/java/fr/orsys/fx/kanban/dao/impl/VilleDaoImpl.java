package fr.orsys.fx.kanban.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.fx.kanban.business.Ville;
import fr.orsys.fx.kanban.dao.ConnexionBdd;
import fr.orsys.fx.kanban.dao.Requetes;
import fr.orsys.fx.kanban.dao.VilleDao;

public class VilleDaoImpl implements VilleDao {
	private Connection connexion;

	public VilleDaoImpl() {
		try {
			connexion = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Ville findOne(Long id) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.RECUPERATION_VILLE_PAR_ID);
		preparedStatement.setLong(1, id);
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			String codeInsee = rs.getString("code_insee");
			String nom = rs.getString("nom");
			String codePostal = rs.getString("code_postal");

			String complement = rs.getString("complement");
			double latitude = rs.getDouble("latitude");
			double longitude = rs.getDouble("longitude");

			Ville ville = new Ville(codeInsee, nom, codePostal);

			ville.setComplement(complement);
			ville.setLatitude(latitude);
			ville.setLongitude(longitude);

			return ville;
		}
		return null;
	}

	@Override
	public Ville create(Ville ville) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.AJOUT_VILLE,
				// On souhaite récupérer l'id que MySQL a attribué à la nouvelle colonne
				Statement.RETURN_GENERATED_KEYS);

		// On remplace le premier point d'interrogation par le nom de la colonne
		preparedStatement.setString(1, ville.getCodePostal());
		preparedStatement.setString(2, ville.getNom());
		preparedStatement.setString(3, ville.getCodeInsee());
		preparedStatement.setString(4, ville.getComplement());
		preparedStatement.setDouble(5, ville.getLatitude());
		preparedStatement.setDouble(6, ville.getLongitude());
		// On demande l'exécution de la requête SQL à MySQL
		preparedStatement.executeUpdate();

		// On récupère l'id attribué par MySQL
		ResultSet rs = preparedStatement.getGeneratedKeys();

		if (rs.next()) {
			// On lit dans la matrice de résultat la premiere cellule
			// on se sert de cette donnée pour mettre à jour l'id de la colonne
			ville.setId(rs.getLong(1));
		}
		return ville;
	}

	@Override
	public List<Ville> findAll() throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.RECUPERATION_VILLES);
		ResultSet rs = preparedStatement.executeQuery();
		List<Ville> villes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String codeInsee = rs.getString("code_insee");
			String nom = rs.getString("nom");
			String codePostal = rs.getString("code_postal");

			String complement = rs.getString("complement");
			double latitude = rs.getDouble("latitude");
			double longitude = rs.getDouble("longitude");
			
			villes.add(new Ville(id, codeInsee, nom, codePostal, latitude, longitude, complement));
		}
		return villes;
	}

	@Override
	public List<Ville> findByNomStartingWith(String nomEntree) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.RECUPERATION_VILLE_COMMENCANT_PAR_NOM);
		preparedStatement.setString(1,  nomEntree);
		ResultSet rs = preparedStatement.executeQuery();
		
		List<Ville> villes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String codeInsee = rs.getString("code_insee");
			String nom = rs.getString("nom");
			String codePostal = rs.getString("code_postal");

			String complement = rs.getString("complement");
			double latitude = rs.getDouble("latitude");
			double longitude = rs.getDouble("longitude");
			
			villes.add(new Ville(id, codeInsee, nom, codePostal, latitude, longitude, complement));
		}
		
		return villes;
	
	}

	@Override
	public String countVille() throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(Requetes.RECUPERATION_COUNT_VILLE);
		ResultSet rs = preparedStatement.executeQuery();
		
		//Retrieving the result
	      rs.next();
	      int count = rs.getInt(1);
	      String countString = "" + count;

		return countString;
		
	}

}
