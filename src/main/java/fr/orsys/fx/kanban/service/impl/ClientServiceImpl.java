package fr.orsys.fx.kanban.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.orsys.fx.kanban.business.Client;

public class ClientServiceImpl implements fr.orsys.fx.kanban.service.ClientService {
	List<Client> clients = new ArrayList<>();

	@Override
	public Client ajouterClient(Client client) {
		clients.add(client);
		return client;
	}

	@Override
	public Client recupererClient(Long id) {
		for (Client client: clients) {
			if (client.getId().equals(id)) {
				return client;
			}
		}
		
		return null;
	}

	@Override
	public List<Client> recupererClients() {
		return clients;
	}

	@Override
	public boolean supprimerClient(Long id) {
		Client clientASupprimer = recupererClient(id);
		
		if (clientASupprimer == null) {
			return false;
		} else {
			return clients.remove(clientASupprimer);
		}
	}

}
