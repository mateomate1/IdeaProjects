package com.dam.di.gestion.gestiondeservicios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    public void abrirAlta() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Alta.fxml"));
        Parent nuevaPantalla = loader.load();

        // Obtener el Stage actual desde cualquier componente en la escena
        Stage stage = (Stage) nuevaPantalla.getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(nuevaPantalla));

        Stage stageActual = (Stage) nuevaPantalla.getScene().getWindow();

    }
    @FXML
    public void abrirLogin(){

    }
}