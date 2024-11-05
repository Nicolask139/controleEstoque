package com.controleEstoque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private TextField nomeField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> {
            String nome = nomeField.getText();
            String senha = senhaField.getText();

            loginTrue(event, nome, senha);
        });
    }

    private void loginTrue(ActionEvent event, String nome, String senha) {
        boolean autenticado = autenticacao(nome, senha);
        if (autenticado) {
            try {
                // Carrega o arquivo FXML da tela principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Controle.fxml"));
                Parent root = loader.load();

                // Define a nova cena
                Scene scene = new Scene(root);

                // Obtem o Stage atual (janela) a partir do evento
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Configura a nova cena
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Adicione tratamento de erro adequado
            }
        } else {
            System.out.println("Erro: Nome de usuário ou senha incorretos.");
        }
    }

    private boolean autenticacao(String nome, String senha) {
        String url = "jdbc:mysql://localhost:3306/controleEstoque_db";
        String dbUsuario = "root";
        String dbSenha = "imbriani10";

        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbSenha);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, senha);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Retorna true se houver pelo menos um resultado
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao autenticar o usuário: " + e.getMessage());
            return false;
        }
    }


    public void mostrarTelaLogin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Tela de Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
