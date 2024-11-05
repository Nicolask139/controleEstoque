package com.controleEstoque.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.controleEstoque.funcoes.Program.createItem;

public class CadastrarController {

    @FXML
    private TextField createNome;
    @FXML
    private TextField createPreco;
    @FXML
    private TextField createQuantidade;
    @FXML
    private Button createCadastrar;

    @FXML
    private Button controleEstoqueButton;
    @FXML
    private Button fornecedoresButton;
    @FXML
    private Button clientesButton;
    @FXML
    private Button historicoButton;
    @FXML
    private Button cadastrarProdutoButton;
    @FXML
    private Button atualizarEstoqueButton;

    public void initialize() {
        // Configurando eventos de ação para os botões
        controleEstoqueButton.setOnAction(event -> loadScreen("/view/Controle.fxml"));
        fornecedoresButton.setOnAction(event -> loadScreen("/view/Fornecedores.fxml"));
        clientesButton.setOnAction(event -> loadScreen("/view/Clientes.fxml"));
        historicoButton.setOnAction(event -> loadScreen("/view/Historico.fxml"));
        atualizarEstoqueButton.setOnAction(event -> loadScreen("/view/Atualizar.fxml"));
    }

    private void loadScreen(String fxmlFile) {
        // Obtém o Stage atual no momento do evento
        Stage currentStage = (Stage) controleEstoqueButton.getScene().getWindow();
        // Chama o método da superclasse RouteController
        new RouteController().loadScreen(currentStage, fxmlFile);
    }

    @FXML
    public void acaoCadastrar() {
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

}
