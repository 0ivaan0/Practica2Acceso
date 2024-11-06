package service;

import dao.ConnectionManager;
import dao.IProductDao;
import dao.impl.ProductDaoJdbc;
import model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private IProductDao productDao;

    public Product newProduct(Product toCreate) {

        toCreate.setCreateDate(LocalDateTime.now());
        toCreate.setUpdateDate(LocalDateTime.now());

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            productDao=new ProductDaoJdbc(conn);
            int id = productDao.insert(toCreate);
            toCreate.setId(id);

            logger.info("Product created sucessfully");

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }

        return toCreate;
    }

    public Product updateProduct(Product toUpdate) {

        toUpdate.setUpdateDate(LocalDateTime.now());

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            productDao=new ProductDaoJdbc(conn);
            productDao.update(toUpdate);

            logger.info("Product modificated sucessfully");

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }
        return toUpdate;
    }
}
