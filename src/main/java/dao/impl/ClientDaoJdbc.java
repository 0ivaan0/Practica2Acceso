package dao.impl;

import dao.ConnectionManager;
import dao.IClientDao;
import model.Client;

import java.sql.Connection;
import java.util.List;

public class ClientDaoJdbc implements IClientDao {

    private Connection connection;

    public ClientDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Client toCreate) {
        return 0;
    }

    @Override
    public boolean update(Client toModify) {
        return false;
    }

    @Override
    public boolean delete(int idToDelete) {
        return false;
    }

    @Override
    public int incrementPurchases(int clientId, int amount) {
        return 0;
    }

    @Override
    public Client getById(int clientId) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return List.of();
    }

    @Override
    public Client getByEmail(String email) {
        return null;
    }
}
