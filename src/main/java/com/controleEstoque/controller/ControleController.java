package com.controleEstoque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


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
    private Button atualizarEstoqueButton;
    @FXML
    private Button fornecedoresButton;
    @FXML
    private Button clientesButton;
    @FXML
    private Button historicoButton;
}
