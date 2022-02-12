package fr.orsys.fx.kanban;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.orsys.fx.kanban.business.Colonne;
import fr.orsys.fx.kanban.service.ColonneService;
import fr.orsys.fx.kanban.service.TacheService;
import fr.orsys.fx.kanban.service.TypeTacheService;
import fr.orsys.fx.kanban.service.VilleService;
import fr.orsys.fx.kanban.service.impl.ColonneServiceImpl;
import fr.orsys.fx.kanban.service.impl.TacheServiceImpl;
import fr.orsys.fx.kanban.service.impl.TypeTacheServiceImpl;
import fr.orsys.fx.kanban.service.impl.VilleServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	private static Point point = new Point();

	private static ColonneService colonneService = new ColonneServiceImpl();
	private static VilleService villeService = new VilleServiceImpl();
	private static TacheService tacheService = new TacheServiceImpl();
	private static TypeTacheService typeTacheService = new TypeTacheServiceImpl();
	

	public static void main(String[] args) {
		
		try {
			try {
				villeService.importerVilles();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(villeService.recupererVilles());
		
		colonneService.ajouterColonne("fabulous");
		if (colonneService.recupererColonnes().isEmpty()) {
			colonneService.ajouterColonne("A faire");
			colonneService.ajouterColonne("En cours");
			colonneService.ajouterColonne("A tester");
			colonneService.ajouterColonne("Terminé");
		}

		final JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = frame.getLocation();
				frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});

		frame.setSize(800, 300);
		frame.setLocation(200, 200);

		int x = 0;
		for (Colonne colonne : colonneService.recupererColonnes()) {

			JLabel etiquette = new JLabel(colonne.getNom(), JLabel.CENTER);
			etiquette.setBounds(x, 0, 200, 20);
			frame.getContentPane().add(etiquette);
			x += 200;
		}

		x = 0;
		List<JPanel> panels = new ArrayList<>();

		for (Colonne colonne : colonneService.recupererColonnes()) {

			JPanel panel = new JPanel();
			panel.setBounds(x, 0, 200, 276);
			panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			panel.setLayout(null);
			panels.add(panel);

			x += 200;
		}

		Collections.reverse(panels);
		for (JPanel jPanel : panels) {
			frame.getContentPane().add(jPanel);
		}

		Collections.reverse(panels);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu);

		JMenu menu2 = new JMenu("Tâches");
		menuBar.add(menu2);

		JMenu menu3 = new JMenu("A propos");
		menuBar.add(menu3);

		JMenuItem item = new JMenuItem("Exit");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		menu.add(item);

		frame.setJMenuBar(menuBar);

		frame.setVisible(true);
	}

}