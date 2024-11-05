package com.controleEstoque.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class AtualizarController {

    // Menu e botões laterais
    @FXML
    private MenuItem newMenuItem, openMenuItem, closeMenuItem, saveMenuItem, saveAsMenuItem, quitMenuItem;
    @FXML
    private MenuItem undoMenuItem, redoMenuItem, cutMenuItem, copyMenuItem, pasteMenuItem, deleteMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Button btnControleEstoque, btnAtualizarEstoque, btnFornecedores, btnClientes, btnHistorico;

    // Campos de entrada de dados
    @FXML
    private TextField entradaQuantidade, entradaData, entradaFornecedor, entradaLote;
    @FXML
    private TextField saidaQuantidade, saidaData, saidaCliente, saidaLote;
    @FXML
    private Label statusLeft, statusRight;
}
