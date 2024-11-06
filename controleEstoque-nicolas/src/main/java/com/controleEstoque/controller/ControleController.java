package com.controleEstoque.controller;

import com.controleEstoque.db.DB;
import com.controleEstoque.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControleController {

    @FXML
    private Label totalEstoqueLabel;
    @FXML
    private Label disponivelVendaLabel;
    @FXML
    private Label reservadosLabel;

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
    @FXML
    private TextField nomeProdutoField;

    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn<Produto, Integer> colunaId;
    @FXML
    private TableColumn<Produto, String> colunaNome;
    @FXML
    private TableColumn<Produto, Double> colunaPreco;
    @FXML
    private TableColumn<Produto, Integer> colunaQuantidade;

    @FXML
    private Button pesquisarButton;

    public void initialize() {
        // Configura a navegação entre telas
        atualizarEstoqueButton.setOnAction(event -> loadScreen("/view/Atualizar.fxml"));
        fornecedoresButton.setOnAction(event -> loadScreen("/view/Fornecedores.fxml"));
        clientesButton.setOnAction(event -> loadScreen("/view/Clientes.fxml"));
        historicoButton.setOnAction(event -> loadScreen("/view/Historico.fxml"));
        cadastrarProdutoButton.setOnAction(event -> loadScreen("/view/Cadastrar.fxml"));

        // Configura as colunas da TableView com o modelo Produto
        if (tabelaProdutos != null) {
            colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
            colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        }

        // Configura o botão de pesquisa
        pesquisarButton.setOnAction(event -> acaoPesquisaNome());
    }

    private void loadScreen(String fxmlFile) {
        Stage currentStage = (Stage) controleEstoqueButton.getScene().getWindow();
        new RouteController().loadScreen(currentStage, fxmlFile);
    }

    public void acaoPesquisaNome() {
        String nomeProduto = nomeProdutoField.getText();

        if (nomeProduto.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "O campo de nome não pode estar vazio.");
            alert.showAndWait();
            return;
        }
        pesquisarNome(nomeProduto);
    }

    private void pesquisarNome(String nome) {
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        String sql = "SELECT * FROM produto WHERE Nome LIKE ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, "%" + nome + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nomeProduto = rs.getString("Nome");
                    double preco = rs.getDouble("Preco");
                    int quantidade = rs.getInt("Quantidade");

                    produtos.add(new Produto(id, nomeProduto, preco, quantidade));
                }
            }

            if (tabelaProdutos != null) {
                tabelaProdutos.setItems(produtos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao buscar produtos: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public TextField getNomeProdutoField() {
        return nomeProdutoField;
    }

    public void setNomeProdutoField(TextField nomeProdutoField) {
        this.nomeProdutoField = nomeProdutoField;
    }
}
