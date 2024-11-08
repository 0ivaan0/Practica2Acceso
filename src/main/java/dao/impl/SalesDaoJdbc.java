package dao.impl;

import dao.ISalesDao;
import model.Client;
import model.Product;
import model.Sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


        return null;
    }

    @Override
    public Client getTopPurchasingClient() {
        return null;
    }
}
