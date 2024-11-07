package service;

import dao.ConnectionManager;
import dao.IClientDao;
import dao.IProductDao;
import dao.ISalesDao;
import dao.impl.ClientDaoJdbc;
import dao.impl.ProductDaoJdbc;
import dao.impl.SalesDaoJdbc;
import exceptions.InventoryException;
import model.Client;
import model.Product;
import model.Sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class SalesService {

    private static final Logger logger = LoggerFactory.getLogger(SalesService.class);
    private ISalesDao salesDao;
    private IProductDao productDao;
    private IClientDao clientDao;

    public void newSale(Product product, Client client, int quantity){

        ConnectionManager instance = ConnectionManager.getInstance();
        try (Connection conn = instance.getConnection()) {

            logger.info("Connection successful");

            boolean verify = false;
            boolean verify2 = false;
            if (product.getStock() < quantity){
                throw new InventoryException();
            }

            productDao = new ProductDaoJdbc(conn);
            verify = productDao.subtractStock(product.getId(),quantity);

            if (verify == true) {
                clientDao = new ClientDaoJdbc(conn);
                verify2 = clientDao.incrementPurchases(client.getId(),quantity);
            } else {
                logger.error("Error trying to subtract the product stock from the service newSale.");
            }

            if (verify2 == true) {
                Sales sales = new Sales();

            }



        } catch (SQLException e) {
            logger.error("SQL Exception in NewSale",e);
            //throw new RuntimeException(e);
        } catch (InventoryException e) {
            logger.error(e.getInsufficientStockMessage());
            //throw new RuntimeException(e);
        }

    }

}
