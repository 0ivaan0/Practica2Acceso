package dao.impl;

import dao.ISalesDao;
import model.Client;
import model.Product;
import model.Sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SalesDaoJdbc implements ISalesDao {

    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SalesDaoJdbc.class);

    public SalesDaoJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Sales toCreate) {
        String sql = "INSERT INTO SALES ( product_id, client_id, quantity, date_of_sale) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1,toCreate.getProduct().getId());
            stmt.setInt(2,toCreate.getCustomer().getId());
            stmt.setInt(3,toCreate.getQuantity());
            stmt.setObject(4, toCreate.getDateOfSale());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Insert prepared statement successfully");
            } else {
                logger.error("Inserting sales failed");
            }

        } catch (SQLException e) {
            logger.info("SQL ERROR in InsertSales");
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in InsertSales",e);
        }
    }

    @Override
    public Product getMostPurchasedProduct() {

        Product product = null;

        String sql = "{call GetMostPurchasesProduct()}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("NAME"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setStock(rs.getInt("STOCK"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setAvailable(rs.getBoolean("AVAILABLE"));
                product.setCreateDate(rs.getTimestamp("CREATE_DATE").toLocalDateTime());
                product.setUpdateDate(rs.getTimestamp("UPDATE_DATE").toLocalDateTime());
            }
        } catch (SQLException e) {
            logger.info("SQL ERROR in getMostPurchasedProduct");
            //throw new SQLException("Error executing GetTopPurchasesProduct stored procedure", e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in getMostPurchasedProduct",e);
        }

        return product;
    }

    @Override
    public Client getTopPurchasingClient() {

        Client client = null;

        String sql = "{call GetTopPurchasingClient()}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                client.setEmail(rs.getString("EMAIL"));
                client.setPurchases(rs.getInt("PURCHASES"));
                client.setCreateDate(rs.getTimestamp("CREATE_DATE").toLocalDateTime());
                client.setUpdateDate(rs.getTimestamp("UPDATE_DATE").toLocalDateTime());
            }
        } catch (SQLException e) {
            logger.error("SQL ERROR in getTopPurchasingClient",e);
            //throw new SQLException("Error executing GetTopPurchasingClient stored procedure", e);
        } catch (Exception e) {
            logger.error("GENERAL ERROR in getTopPurchasingClient",e);
        }

        return client;
    }
}
