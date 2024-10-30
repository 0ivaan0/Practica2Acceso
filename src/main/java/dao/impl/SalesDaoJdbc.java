package dao.impl;

import dao.ISalesDao;

import java.sql.Connection;

public class SalesDaoJdbc implements ISalesDao {

    private Connection connection;

    public SalesDaoJdbc(Connection connection) {
        this.connection = connection;
    }
}
