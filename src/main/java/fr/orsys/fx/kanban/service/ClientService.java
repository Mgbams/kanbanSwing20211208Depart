package fr.orsys.fx.kanban.service;

import java.util.List;

import fr.orsys.fx.kanban.business.Client;

public interface ClientService {

	Client ajouterClient(Client client);

	Client recupererClient(Long id);

	List<Client> recupererClients();

	boolean supprimerClient(Long id);
}
