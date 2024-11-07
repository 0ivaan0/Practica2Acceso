package service;

import dao.ConnectionManager;
import dao.IClientDao;
import dao.impl.ClientDaoJdbc;
import model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private IClientDao clientDao;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    public Client newClient(Client toCreate) {

        toCreate.setCreateDate(LocalDateTime.now());
        toCreate.setUpdateDate(LocalDateTime.now());

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            clientDao = new ClientDaoJdbc(conn);
            int id = clientDao.insert(toCreate);
            toCreate.setId(id);

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }

        return toCreate;
    }

    public Client updateClient(Client newInfo) {

        newInfo.setUpdateDate(LocalDateTime.now());

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            clientDao = new ClientDaoJdbc(conn);
            boolean verify = clientDao.update(newInfo);


        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }
        return newInfo;
    }

    public boolean deleteClient(Client clientId) {

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            clientDao = new ClientDaoJdbc(conn);
            boolean verify = clientDao.delete(clientId.getId());
            return verify;

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }
        return false;
    }

//    public boolean incrementPurchases(int clientId, int amount) {
//
//        ConnectionManager instance = ConnectionManager.getInstance();
//        try(Connection conn = instance.getConnection()){
//
//            logger.info("Connection sucessful");
//
//            clientDao = new ClientDaoJdbc(conn);
//            boolean verify = clientDao.incrementPurchases();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    public List<Client> getAllClients() {

        List<Client> clientList = new ArrayList<>();

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            clientDao = new ClientDaoJdbc(conn);
            clientList = clientDao.getAll();
            return clientList;

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }

        return clientList;
    }

    public Client getClientByEmail(String email) {

        Client client = new Client();

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            clientDao = new ClientDaoJdbc(conn);
            client = clientDao.getByEmail(email);

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }

        return client;
    }
}
