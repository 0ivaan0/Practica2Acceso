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
import java.util.ArrayList;
import java.util.List;

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
            boolean verify = productDao.update(toUpdate);

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }
        return toUpdate;
    }

    public boolean deleteProduct(Product toDelete){

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            productDao=new ProductDaoJdbc(conn);
            boolean verify = productDao.delete(toDelete.getId());
            return verify;

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        }catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }
        return false;
    }

    public Product getById(int productId){

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            productDao = new ProductDaoJdbc(conn);
            Product productById = productDao.getById(productId);

            return productById;

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }
        return null;
    }

    public List<Product> getAllProducts(){

        List<Product> productList = new ArrayList<>();
        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            productDao = new ProductDaoJdbc(conn);
            productList  = productDao.getAll();

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }

        return productList;
    }

    public List<Product> getProdcutsByNameALike(Product searchByName){

        List<Product> productList = new ArrayList<>();
        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){

            logger.info("Connection sucessful");

            productDao = new ProductDaoJdbc(conn);
            productList = productDao.getAllByNameAlike(searchByName.getName());

        } catch (SQLException e) {
            logger.error("There was an error trying to establish the connection",e);
            //throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("General error",e);
            //throw new RuntimeException(e);
        }

        return productList;
    }
}
