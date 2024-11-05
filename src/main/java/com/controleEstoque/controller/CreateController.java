package com.controleEstoque.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.controleEstoque.funcoes.Program.createItem;

public class CreateController {

    // Menu e botões laterais
    @FXML
    private TextField createNome;  // Campo de texto para o nome do usuário

    @FXML
    private TextField createPreco; // Campo de senha

    @FXML
    private TextField createQuantidade;
    // Botão de login
    @FXML
    private Button createCadastrar, btnAtualizarEstoque, btnFornecedores, btnClientes, btnHistorico;

    // Campos de entrada de dados
    @FXML
    private TextField entradaQuantidade, entradaData, entradaFornecedor, entradaLote;
    @FXML
    private TextField saidaQuantidade, saidaData, saidaCliente, saidaLote;
    @FXML
    private Label statusLeft, statusRight;

    @FXML
    public void acaoCadastrar() {
        // Adiciona uma ação ao botão de login
        createCadastrar.setOnAction(event -> {
            String nome_create = createNome.getText();
            double preco = Double.parseDouble(createPreco.getText());
            int quantidade = Integer.parseInt(createQuantidade.getText());


            cadastrar(nome_create, preco, quantidade);
        });

    }
    public String cadastrar(String nome_create, double preco, int quantidade){
        createItem(nome_create,preco,quantidade);
        return null;
    }

    public void mostrarTelaLogin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create.fxml"));
            loader.setController(this); // Adiciona o controlador atual ao FXML
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Tela de Criação");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
