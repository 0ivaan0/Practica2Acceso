import dao.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {

//    public static final String URL_CONEX_BD = "jdbc:mysql://localhost:3306/practicaUt2Acceso";
//    public static final String USUARIO_BD = "root";
//    public static final String PASS_BD = "";

    public static void main(String[] args) {

//        try {
//            Connection conexion = DriverManager.getConnection(URL_CONEX_BD, USUARIO_BD, PASS_BD);
//            System.out.println("Conexión exitosa");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        ConnectionManager connectionManager = ConnectionManager.getInstance();

        try (Connection conn = connectionManager.getConnection()) {
            System.out.println("Conexion hecha!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
