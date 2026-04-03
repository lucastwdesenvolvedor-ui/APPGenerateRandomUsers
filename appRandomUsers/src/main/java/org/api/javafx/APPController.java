package org.api.javafx;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.api.getApi.HttpClientLib;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.api.checagem.check.*;

public class APPController {
    static Dotenv dotenv = Dotenv.load();
    String key = dotenv.get("API_DB_KEY");
    private static final String linkRandom = dotenv.get("API_RAND_USERS");
    private static final String db = dotenv.get("API_DB");

    @FXML
    private HBox connOPT;

    @FXML
    private Button btn_login, btn_register;

    @FXML
    private VBox login;

    @FXML
    private Button set_login, redirect_register;

    @FXML
    private TextField loginUsername, loginEmail, loginPassword;

    @FXML
    private Label msgLogin;

    @FXML
    private VBox register;

    @FXML
    private Button set_register, redirect_login;

    @FXML
    private TextField registreUsername, registreEmail, registrePassword;

    @FXML
    private Label msgRegister;

    @FXML
    private VBox gerador;

    @FXML
    private Label Usuarios;

    @FXML
    private TextField ninput, pinput;


    public void loginPage() {
        gerador.setVisible(false);
        gerador.setManaged(false);

        connOPT.setVisible(false);
        connOPT.setManaged(false);

        register.setVisible(false);
        register.setManaged(false);

        login.setVisible(true);
        login.setManaged(true);

    }

    public void registerPage() {
        gerador.setVisible(false);
        gerador.setManaged(false);

        connOPT.setVisible(false);
        connOPT.setManaged(false);

        register.setVisible(true);
        register.setManaged(true);

        login.setVisible(false);
        login.setManaged(false);

    }

    public void randomPage() {
        gerador.setVisible(true);
        gerador.setManaged(true);

        connOPT.setVisible(true);
        connOPT.setManaged(true);

        register.setVisible(false);
        register.setManaged(false);

        login.setVisible(false);
        login.setManaged(false);

    }

    @FXML
    public void initialize() {

        loginPage();
        set_login.setOnAction(e -> {

            String Username = loginUsername.getText();
            String Email = loginEmail.getText();
            String Password = loginPassword.getText();

            if (Username.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
                msgLogin.setText("Preencha todos os campos.");
                return;
            }

            try {

                String req = HttpClientLib.get(
                        db + "/get?key=" + key + "&nome=" + Username + "&senha=" + Password + "&email=" + Email
                );


                randomPage();
            }catch (Exception ex) {
                msgLogin.setText("Login inválido.");
            }
        });
        set_register.setOnAction(e -> {

            String nome = registreUsername.getText();
            String email = registreEmail.getText();
            String senha = registrePassword.getText();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                msgRegister.setText("Preencha tudo");
                return;
            }

            String verificarEmail = "^[\\w!#$%&'*+/=?^_{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
            // ✅ RESTAURADO: validação de email

            if (!email.matches(verificarEmail)) {
                msgRegister.setText("insira um email valido");
                return;
            }

            if (!(checkPass(senha).equals(""))) {
                msgRegister.setText(checkPass(senha));
                return;
            }
            // ✅ RESTAURADO: validação de senha

            try {

                HttpClientLib.post(
                        db,
                        key,
                        nome,
                        email,
                        senha,
                        "/post"
                );

                randomPage();

            } catch (Exception ex) {
                msgRegister.setText("Erro no cadastro");
            }
        });


        btn_login.setOnAction(event -> {
            loginPage();
        });

        btn_register.setOnAction(event -> {
            registerPage();
        });

        redirect_register.setOnAction(event -> {
            registerPage();
        });
        redirect_login.setOnAction(event -> {
            loginPage();
        });

    }

    @FXML
    protected void aoClicarNoBotao() throws Exception {

        String numero = ninput.getText();
        String pais = pinput.getText().toUpperCase();

        if (numero.isEmpty() || pais.isEmpty()) {
            Usuarios.setText("Preencha os campos para gerar os usuários.");
            return;
        }

        if (!checkCountry(pais)) {
            Usuarios.setText("Código do país inválido. Digite um código válido. paises disponiveis AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IN, IR, MX, NL, NO, NZ, RS, TR, UA, US.");
            return;
        }

        if (!checkNumber(numero)) {
            Usuarios.setText("Número de usuários inválido. Digite um número entre 1 e 100.");
            return;
        } else {
            Usuarios.setText("Gerando usuários...");
        }

        String url = linkRandom + "/users?pais=" + pais + "&count=" + numero;
        String req = HttpClientLib.get(url);

        JSONArray results = new JSONArray(req);

        for (int i = 0; i < results.length(); i++) {

            JSONObject user = results.getJSONObject(i);

            String nome = user.getString("nome");
            String username = user.getString("username");
            String email = user.getString("email");
            String cell = user.getString("cell");
            String paisUser = user.getString("pais");
            String cidade = user.getString("cidade");

            if (Usuarios.getText().equals("Gerando usuários...")) {
                Usuarios.setText("");
            }

            Usuarios.setText(
                    Usuarios.getText()
                            + "\nNome: " + nome
                            + "\nUsername: " + username
                            + "\nEmail: " + email
                            + "\nCell: " + cell
                            + "\nPaís: " + paisUser
                            + "\nCidade: " + cidade
                            + "\n\n"
            );
        }
    }
}