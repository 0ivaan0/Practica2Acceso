package dao.impl;

import dao.IProductDao;
import model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ProductService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            logger.error("SQL ERROR in InsertProduct",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in InsertProduct",e);
        }
        return -1;
    }

    @Override
    public boolean update(Product toModify) {
        String sql = "UPDATE PRODUCT SET name = ?, description = ?, stock = ?, price = ?, available = ?, update_Date = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, toModify.getName());
            stmt.setString(2, toModify.getDescription());
            stmt.setInt(3, toModify.getStock());
            stmt.setDouble(4, toModify.getPrice());
            stmt.setBoolean(5, toModify.isAvailable());
            stmt.setObject(6, toModify.getUpdateDate());  // Asumimos que updateDate es de tipo LocalDateTime
            stmt.setInt(7, toModify.getId());  // El ID es necesario para identificar el producto a actualizar

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Modification successful, rows affected: " + rowsAffected);
                return true;  // Retornamos true si al menos una fila fue actualizada
            } else {
                logger.warn("No rows affected, check if the product ID exists");
            }

        } catch (SQLException e) {
            logger.error("SQL ERROR in UpdateProduct",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in UpdateProduct",e);
        }

        return false;
    }

        @Override
    public boolean delete(int idToDelete) {
        String sql = "DELETE FROM PRODUCT WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1,idToDelete);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Product with ID "+idToDelete+" sucessfully deleted");
                return true;
            } else {
                logger.warn("Product ID not found");
            }


        } catch (SQLException e) {
            logger.error("SQL ERROR in DeleteProduct",e);
            //throw new RuntimeException(e);
        }catch (Exception e) {
            logger.error("GENERAL ERROR in DeleteProduct",e);
        }
        return false;
    }

    @Override
    public Product getById(int productId) {
        String sql = "SELECT * FROM PRODUCT WHERE id = ?";
        Product product = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1,productId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                product.setAvailable(rs.getBoolean("available"));
                product.setCreateDate(rs.getObject("create_Date", LocalDateTime.class));
                product.setUpdateDate(rs.getObject("update_Date", LocalDateTime.class));  // Si updateDate es LocalDateTime
            }

        } catch (SQLException e) {
            logger.error("SQL ERROR in GetById",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in GetById",e);
        }

        return product;
    }

    @Override
    public List<Product> getAll() {

        String sql = "SELECT * FROM PRODUCT";
        List<Product> productList = new ArrayList<>();

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                product.setAvailable(rs.getBoolean("available"));
                product.setCreateDate(rs.getObject("create_Date", LocalDateTime.class));
                product.setUpdateDate(rs.getObject("update_Date", LocalDateTime.class));
                productList.add(product);
            }

        } catch (SQLException e) {
            logger.error("SQL ERROR in GetAll",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in GetAll",e);
        }

        return productList;
    }

    @Override
    public List<Product> getAllByNameAlike(String name) {

        String sql = "SELECT * FROM PRODUCT WHERE name = ?";
        List<Product> productList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setPrice(rs.getDouble("price"));
                product.setAvailable(rs.getBoolean("available"));
                product.setCreateDate(rs.getObject("create_Date", LocalDateTime.class));
                product.setUpdateDate(rs.getObject("update_Date", LocalDateTime.class));
                productList.add(product);
            }

        } catch (SQLException e) {
            logger.error("SQL ERROR in getAllByNameAlike",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in getAllByNameAlike",e);
        }

        return productList;
    }

    @Override
    public boolean subtractStock(int idToSubtract, int amount) {
        String sql = "UPDATE PRODUCT SET stock = stock - ? WHERE id = ?";
        boolean verify = false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, amount);
            stmt.setInt(2, idToSubtract);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Stock product updated successfully");
                verify = true;
            } else {
                logger.error("Stock product didn't update, check the ID product");
            }

        } catch (SQLException e) {
            logger.error("GENERAL ERROR in SubtractStock",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in SubtractStock",e);
        }

        return verify;
    }
}
