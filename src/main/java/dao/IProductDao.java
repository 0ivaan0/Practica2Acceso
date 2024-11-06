package dao;

import model.Product;

import java.util.List;

public interface IProductDao {

    int insert(Product toCreate);

    boolean update(Product toModify);

    boolean delete(int idToDelete);

    Product getById(int productId);

    List<Product> getAll();

    List<Product> getAllByNameAlike(String name);

    boolean subtractStock(int idToSubtract, int amount);
}
