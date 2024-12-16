package com.dam.di.gestion.gestiondeservicios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {
    @FXML Button btnAlta;

    @FXML
    public void abrirAlta(ActionEvent actionEvent) throws IOException {
        WindowsHandler.load("Alta.fxml");
        WindowsHandler.close((Stage) btnAlta.getScene().getWindow());
    }

    @FXML
    public void abrirLogin(ActionEvent actionEvent) throws IOException {
        WindowsHandler.load("Login.fxml");
        WindowsHandler.close((Stage) btnAlta.getScene().getWindow());
    }
}