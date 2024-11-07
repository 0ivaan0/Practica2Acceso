package dao;

import model.Client;

import java.util.List;

public interface IClientDao {

    int insert(Client toCreate);

    boolean update(Client toModify);

    boolean delete(int idToDelete);

    boolean incrementPurchases(int clientId, int amount);

    Client getById(int clientId);

    List<Client> getAll();

    Client getByEmail(String email);
}
