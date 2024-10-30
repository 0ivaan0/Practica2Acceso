package service;

import dao.IClientDao;
import model.Client;

import java.util.List;

public class ClientService {
    private final IClientDao clientDao;

    public ClientService(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client newClient(Client client) {
        int id = clientDao.insert(client);
        if (id > 0) {
            client.setId(id);
            return client;
        }
        return null;
    }

    public Client updateClient(Client newInfo) {
        if (clientDao.update(newInfo)) {
            return newInfo;
        }
        return null;
    }

    public boolean deleteClient(int clientId) {
        return clientDao.delete(clientId);
    }

    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    public Client getClientByEmail(String email) {
        return clientDao.getByEmail(email);
    }
}
