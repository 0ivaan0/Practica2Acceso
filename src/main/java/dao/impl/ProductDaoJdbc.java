package dao.impl;

import dao.IProductDao;
import model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ProductService;

import java.sql.*;
import java.util.List;

public class ProductDaoJdbc implements IProductDao {

    private Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoJdbc.class);
    public ProductDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Product toCreate) {
        String sql = "INSERT INTO PRODUCT (name, description, stock, price, available, create_Date, update_Date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, toCreate.getName());
            stmt.setString(2, toCreate.getDescription());
            stmt.setInt(3, toCreate.getStock());
            stmt.setDouble(4, toCreate.getPrice());
            stmt.setBoolean(5, toCreate.isAvailable());
            stmt.setObject(6, toCreate.getCreateDate());
            stmt.setObject(7, toCreate.getUpdateDate());
            logger.info("Insert prepared statement sucessfully");

            // Ejecutamos la inserción
            int rowsAffected = stmt.executeUpdate();

            // Si la inserción fue exitosa, obtenemos el ID generado
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Recuperamos el ID generado y lo asignamos al producto
                        return generatedKeys.getInt(1);  // Retorna el ID generado
                    }
                    else {
                        logger.error("Creating product failed, no ID obtained.");
                        throw new SQLException("Creating product failed, no ID obtained.");
                    }
                }
            }

            //return stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Insert prepared statement error");
            throw new RuntimeException("Error inserting product", e);
        }
        return -1;
    }

    @Override
    public boolean update(Product toModify) {
        String sql = "UPDATE PRODUCT SET name = ?, description = ?, stock = ?, price = ?, available = ?, updateDate = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, toModify.getName());
            stmt.setString(2, toModify.getDescription());
            stmt.setInt(3, toModify.getStock());
            stmt.setDouble(4, toModify.getPrice());
            stmt.setBoolean(5, toModify.isAvailable());
            stmt.setObject(6, toModify.getUpdateDate());  // Asumimos que updateDate es de tipo LocalDateTime
            stmt.setInt(7, toModify.getId());  // El ID es necesario para identificar el producto a actualizar

            stmt.executeUpdate();

            logger.info("Modification prepared statement sucessfully");

        } catch (SQLException e) {
            logger.error("Update prepared statement error");
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("Update prepared statement general error");
        }

        return false;
    }

        @Override
    public boolean delete(int idToDelete) {
        return false;
    }

    @Override
    public Product getById(int productId) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }

    @Override
    public List<Product> getAllByNameAlike(String name) {
        return List.of();
    }

    @Override
    public boolean subtractStock(int idToSubtract, int amount) {
        return false;
    }
}
