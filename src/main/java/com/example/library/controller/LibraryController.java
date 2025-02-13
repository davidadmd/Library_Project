package com.example.library.controller;

import com.example.library.dao.BookDAO;
import com.example.library.model.Book;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;

public class LibraryController {
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> editionColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> statusColumn;

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField editionField;

    private BookDAO bookDAO;

    public void initialize() {
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        genreColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGenre()));
        editionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEdition()));
        isbnColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsbn()));
        statusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        try {
            bookDAO = new BookDAO();
            bookTable.getItems().addAll(bookDAO.getAllBooks());
        } catch (SQLException e) {
            showErrorDialog("Erreur lors du chargement des livres : " + e.getMessage());
        }
    }

    @FXML
    private void addBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String genre = genreField.getText().trim();
        String edition = editionField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || edition.isEmpty()) {
            showErrorDialog("Veuillez remplir tous les champs !");
            return;
        }

        Book book = new Book(0, title, author, generateISBN(), "Available", genre, edition);

        try {
            bookDAO.addBook(book);
            bookTable.getItems().add(book);
            clearFields();
            showInfoDialog("Livre ajouté avec succès !");
        } catch (SQLException e) {
            showErrorDialog("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    @FXML
    private void deleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showErrorDialog("Veuillez sélectionner un livre à supprimer !");
            return;
        }

        try {
            bookDAO.deleteBook(selectedBook.getId()); // Appel à la méthode delete dans BookDAO
            bookTable.getItems().remove(selectedBook); // Retirer le livre de la TableView
            showInfoDialog("Livre supprimé avec succès !");
        } catch (SQLException e) {
            showErrorDialog("Erreur lors de la suppression du livre : " + e.getMessage());
        }
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        genreField.clear();
        editionField.clear();
    }

    private String generateISBN() {
        return "ISBN" + System.currentTimeMillis();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
