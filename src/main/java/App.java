import dao.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        ConnectionManager instance = ConnectionManager.getInstance();
        try(Connection conn = instance.getConnection()){
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
