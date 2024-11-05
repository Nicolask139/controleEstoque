package com.controleEstoque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControleController {

    @FXML
    private Label totalEstoqueLabel;
    @FXML
    private Label disponivelVendaLabel;
    @FXML
    private Label reservadosLabel;

    // Botões do menu lateral
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
        // Defina o evento de ação nos botões
        atualizarEstoqueButton.setOnAction(event -> loadScreen("/view/Atualizar.fxml"));
        fornecedoresButton.setOnAction(event -> loadScreen("/view/Fornecedores.fxml"));
        clientesButton.setOnAction(event -> loadScreen("/view/Clientes.fxml"));
        historicoButton.setOnAction(event -> loadScreen("/view/Historico.fxml"));
        cadastrarProdutoButton.setOnAction(event -> loadScreen("/view/Cadastrar.fxml"));
    }

    private void loadScreen(String fxmlFile) {
        // Obtém o Stage atual no momento do evento
        Stage currentStage = (Stage) controleEstoqueButton.getScene().getWindow();
        // Chama o método da superclasse RouteController
        new RouteController().loadScreen(currentStage, fxmlFile);
    }
}
