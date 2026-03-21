package db.sql.mysqlDB.controller;

import db.sql.mysqlDB.Documentacao.doc;
import db.sql.mysqlDB.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class controller {
    String API_KEY = System.getenv("key");
    String host = System.getenv("MYSQLHOST");
    String port = System.getenv("MYSQLPORT");
    String db = System.getenv("MYSQLDATABASE");
    String user = System.getenv("MYSQLUSER");
    String pass = System.getenv("MYSQLPASSWORD");

    String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC";

    @RequestMapping("/")
    public ArrayList<doc> bemVindo() {
        doc post = new doc("/post?", "Requer parâmetros: (KEY) nome=, &email=, &senha= , &log=");
        ArrayList<doc> dc = new ArrayList<>();
        dc.add(new doc("Bem Vindo", "/"));
        dc.add(new doc("Pegar dados do DB", "/get?(KEY)&"));
        dc.add(new doc("Inserir dados no DB", post + " ||log = logado(1) / não loado(0) "));
        return dc;
    }

    public Connection Conn(String url, String user, String password) {
        Connection conn = null;
        try {
            conn = java.sql.DriverManager.getConnection(url, user, password);
            return conn;
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return conn;
        }

    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam String key) throws SQLException {
        ArrayList<Usuario> u = new ArrayList<>();

        if (!API_KEY.equals(key) || key == null) {
            return ResponseEntity.status(401).body("api key não correspondente");
        }


        Connection conn = Conn(url, user, pass);
        if (conn == null) {
            return ResponseEntity.status(500).body("erro ao se conectar com o Banco de Dados");
        }

        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                Usuario user = new Usuario(res.getInt("id"), res.getString("nome"), res.getString("email"), res.getString("pass"));
                u.add(user);
            }
            return ResponseEntity.ok(u);
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("SQL ERROR:" + e.getMessage());
        }

    }

    @PostMapping("/post")
    public ResponseEntity<?> post(
            @RequestParam String key,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String log
    ) {


        if (API_KEY == null || !API_KEY.equals(key)) {
            return ResponseEntity.status(401).body("api key não correspondente");
        }

        if (!(log.equals("1") || log.equals("0"))) {
            return ResponseEntity.status(401).body("O usuario deve receber o valor de 0 ou 1 para deslogado ou logado");
        }
        String sql = "INSERT INTO usuarios (nome, email, pass, logado) VALUES (?, ?, ?,?)";
        try (Connection conn = Conn(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senha);
            ps.setString(4, log);
            ps.executeUpdate();


            return ResponseEntity.ok("True");
        } catch (SQLException e) {
            return ResponseEntity.status(401).body("SQL ERROR: " + e.getMessage());
        }

    }

}
