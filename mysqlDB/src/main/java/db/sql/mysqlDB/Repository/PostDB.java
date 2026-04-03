package db.sql.mysqlDB.Repository;

import db.sql.mysqlDB.Model.Usuario;
import org.springframework.http.ResponseEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostController {
    public static Boolean post(String API_KEY , String key , DBConfig db , Usuario usuario) {


        if (API_KEY == null || !API_KEY.equals(key)) {
            return ResponseEntity.status(401).body("api key não correspondente");
        }


        String sql = "INSERT INTO usuarios (nome, email, pass) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDB.connection(
                db.getUrl(),
                db.getUser(),
                db.getPassword());
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            String NameSet = usuario.getNome();
            String EmailSet = usuario.getEmail();
            String PassSet = usuario.getSenha();
            ps.setString(1, NameSet);
            ps.setString(2, EmailSet);
            ps.setString(3, PassSet);
            ps.executeUpdate();


            return ResponseEntity.ok("True");
        } catch (SQLException e) {
            return ResponseEntity.status(401).body("SQL ERROR: " + e.getMessage());
        }

    }
}
