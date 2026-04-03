package db.sql.mysqlDB.Repository;

import db.sql.mysqlDB.Model.Usuario;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetController {
    public static ResponseEntity<?> get(String API_KEY , String key , String url , String user , String pass){
        ArrayList<Usuario> u = new ArrayList<>();

        if (!API_KEY.equals(key) || key == null) {
            return ResponseEntity.status(401).body("api key não correspondente");
        }


        Connection conn = ConnectionDB.connection(url, user, pass);
        if (conn == null) {
            return ResponseEntity.status(500).body("erro ao se conectar com o Banco de Dados");
        }

        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                Usuario userObjt = new Usuario(res.getInt("id"), res.getString("nome"), res.getString("email"), res.getString("pass"));
                u.add(userObjt);
            }
            return ResponseEntity.ok(u);
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("SQL ERROR:" + e.getMessage());
        }

    }
}
