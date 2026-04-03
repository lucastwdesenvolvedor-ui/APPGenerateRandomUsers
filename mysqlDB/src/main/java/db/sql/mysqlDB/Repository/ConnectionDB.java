package db.sql.mysqlDB.Repository;

import java.sql.Connection;

public class Conectiondb {

    public static Connection connection(String url, String user, String password) {
        Connection conn = null;
        try {
            conn = java.sql.DriverManager.getConnection(url, user, password);
            return conn;
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }

    }
}
