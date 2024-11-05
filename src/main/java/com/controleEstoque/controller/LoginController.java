package com.controleEstoque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField nomeField;  // Campo de texto para o nome do usuário

    @FXML
    private PasswordField senhaField; // Campo de senha

    @FXML
    private Button loginButton;        // Botão de login

    @FXML
    public void initialize() {
        // Adiciona uma ação ao botão de login
        loginButton.setOnAction(event -> {
            String nome = nomeField.getText();
            String senha = senhaField.getText();

            registrarUsuario(nome, senha);
        });
    }

    private void registrarUsuario(String nome, String senha) {
        String url = "jdbc:mysql://localhost:3306/estoque_db";
        String dbUsuario = "root";
        String dbSenha = "imbriani10";

        try (Connection conn = DriverManager.getConnection(url, dbUsuario, dbSenha)) {
            String sql = "INSERT INTO Usuarios (nome, senha) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, senha);
                pstmt.executeUpdate();
                System.out.println("Usuário registrado com sucesso: " + nome);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar o usuário: " + e.getMessage());
        }
    }

    public void mostrarTelaLogin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            loader.setController(this); // Adiciona o controlador atual ao FXML
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Tela de Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
