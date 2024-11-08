package dao;

import model.Client;
import model.Product;
import model.Sales;

public interface ISalesDao {

    void insert(Sales toCreate);

    Product getMostPurchasedProduct();

    Client getTopPurchasingClient();

}
