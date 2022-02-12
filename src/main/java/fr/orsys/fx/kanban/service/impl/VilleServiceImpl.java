package fr.orsys.fx.kanban.service.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import fr.orsys.fx.kanban.business.Ville;
import fr.orsys.fx.kanban.dao.VilleDao;
import fr.orsys.fx.kanban.dao.impl.VilleDaoImpl;
import fr.orsys.fx.kanban.service.VilleService;

public class VilleServiceImpl implements VilleService {
	private List<Ville> villes = new ArrayList<>();
	private VilleDao VilleDao = new VilleDaoImpl();
	private static final String TAILLe_DE_TABLE_VILLE = "39201";

	@Override
	public List<Ville> importerVilles() throws IOException, SQLException {
		try {
			URL url = new URL("https://www.clelia.fr/villes2020.csv");
			// Utilisation de patron Decorateur

			Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()), "UTF-8");

			CSVFormat.Builder csvBuilder = CSVFormat.Builder.create();
			CSVParser parser = csvBuilder.setDelimiter(';').setHeader().build().parse(reader);

			CSVRecord record = null;
			while (parser.iterator().hasNext()) {
				record = parser.iterator().next();
				Ville ville = new Ville(record.get("Code_commune_INSEE"), record.get("Nom_commune"),
						record.get("Code_postal"));

				// On lit les degrés décimaux
				String coordonneesGps = record.get("coordonnees_gps");
				if (!coordonneesGps.equals("")) {
					String[] dd = coordonneesGps.split(",");
					ville.setLatitude(Double.parseDouble(dd[0]));
					ville.setLongitude(Double.parseDouble(dd[1]));
				}
				ville.setComplement(record.get(4));
				villes.add(ville);

			}

			// Vérifier table ville avant l'ajout de contenu.
			if (!VilleDao.countVille().equals(TAILLe_DE_TABLE_VILLE)) {
				for (Ville ville : villes) {
					VilleDao.create(ville);
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return villes;
	}

	@Override
	public Ville ajouterVille(Ville ville) {
		villes.add(ville);
		return ville;
	}

	@Override
	public Ville recupererVille(Long id) {
		try {
			return VilleDao.findOne(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Ville> recupererVilles() {
		try {
			return VilleDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
