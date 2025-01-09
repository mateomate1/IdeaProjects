package com.dam.di.gestion.gestiondeservicios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BibliotecaController implements Initializable {
    @FXML private TableView<Libro> tblLibro;
    @FXML private TableColumn<?,?> colTitulo;
    @FXML private TableColumn<?,?> colAutor;
    @FXML private TableColumn<?,?> colrecio;
    private ObservableList<Libro> libros;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        libros = FXCollections.observableArrayList();

    }
}
