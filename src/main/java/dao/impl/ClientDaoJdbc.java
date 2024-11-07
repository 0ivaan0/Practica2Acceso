package dao.impl;

import dao.ConnectionManager;
import dao.IClientDao;
import exceptions.DuplicateClientException;
import model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class ClientDaoJdbc implements IClientDao {

    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(ClientDaoJdbc.class);

    public ClientDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Client toCreate) {
        String sql = "INSERT INTO CLIENT (name, surname, email, purchases, create_Date, update_Date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1,toCreate.getName());
            stmt.setString(2,toCreate.getSurname());
            stmt.setString(3,toCreate.getEmail());
            stmt.setInt(4, toCreate.getPurchases());
            stmt.setObject(5,toCreate.getCreateDate());
            stmt.setObject(6,toCreate.getUpdateDate());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Recuperamos el ID generado y lo asignamos al producto
                        return generatedKeys.getInt(1);  // Retorna el ID generado
                    }
                    else {
                        logger.error("Creating client failed, no ID obtained.");
                        throw new SQLException("Creating product failed, no ID obtained.");
                    }
                }
            }

        } catch (SQLException e) {
            String error = e.getMessage();
            if (error.contains("client_email_unique")) {
                try {
                    throw new DuplicateClientException();
                } catch (DuplicateClientException dce) {
                    logger.error(dce.getMessageMail() + " " + toCreate.getName());
                }
            } else if (error.contains("PRIMARY")) {
                try {
                    throw new DuplicateClientException();
                } catch (DuplicateClientException dce) {
                    logger.error(dce.getMessageID() + " " + toCreate.getName());
                }
            }
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in InsertClient",e);
        }
        return -1;
    }

    @Override
    public boolean update(Client toModify) {
        String sql = "UPDATE CLIENT SET name = ?, surname = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,toModify.getName());
            stmt.setString(2, toModify.getSurname());
            stmt.setInt(3, toModify.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Modification successful, rows affected: " + rowsAffected);
                return true;  // Retornamos true si al menos una fila fue actualizada
            } else {
                logger.warn("No rows affected, check if the client ID exists");
            }

        } catch (SQLException e) {
            logger.error("SQL ERROR in UpdateClient",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in UpdateClient",e);
        }
        return false;
    }

    @Override
    public boolean delete(int idToDelete) {
        String sql ="DELETE FROM CLIENT WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idToDelete);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Client with ID "+idToDelete+" sucessfully deleted");
                return true;
            } else {
                logger.warn("Client ID not found");
            }

        } catch (SQLException e) {
            logger.error("SQL ERROR in DeleteClient",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in DeleteClient",e);
        }

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
