package com.dam.di.gestion.gestiondeservicios;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AltaContrller {
    @FXML TextField inputNombre;
    @FXML TextField inputApellidos;
    @FXML TextField inputUsername;
    @FXML DatePicker inputFecha;
    @FXML PasswordField inputPassword;

    @FXML
    public void alta() throws IOException {
        Usuario u = new Usuario(inputPassword.getText());
        u.setNombre(inputNombre.getText());
        u.setApellidos(inputApellidos.getText());
        u.setUsername(inputUsername.getText());
        u.setFechaNacimiento(inputFecha.getValue());
        if (u.alta()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("initView.fxml"));
            Parent nuevaPantalla = loader.load();

            // Obtener el Stage actual desde cualquier componente en la escena
            Stage stage = (Stage) nuevaPantalla.getScene().getWindow();

            // Cambiar la escena
            stage.setScene(new Scene(nuevaPantalla));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona añadida");
        }

    }

}
