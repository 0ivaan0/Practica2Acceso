package dao.impl;

import dao.IProductDao;

import java.sql.Connection;

public class ProductDaoJdbc implements IProductDao {

    private Connection connection;

    public ProductDaoJdbc(Connection connection) {
        this.connection = connection;
    }
}
