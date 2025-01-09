package com.dam.di.gestion.gestiondeservicios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AltaContrller {
    @FXML TextField inputNombre;
    @FXML TextField inputApellidos;
    @FXML TextField inputUsername;
    @FXML DatePicker inputFecha;
    @FXML PasswordField inputPassword;
    //Stage stage = (Stage) inputNombre.getScene().getWindow();

    @FXML
    public void alta(ActionEvent actionEvent) throws IOException {
        if (!inputPassword.getText().isBlank()) {
            Usuario u = new Usuario(inputPassword.getText());
            u.setNombre(inputNombre.getText());
            u.setApellidos(inputApellidos.getText());
            u.setUsername(inputUsername.getText());
            u.setFechaNacimiento(inputFecha.getValue());
            if (u.alta()) {
                WindowsHandler.load("initView.fxml");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Persona añadida correctamente");
                alert.showAndWait();
                close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Persona no añadida");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El usuario no tiene contraseña");
            alert.showAndWait();
        }
    }

    @FXML
    public void close() throws IOException {
        WindowsHandler.load("initView.fxml");
        WindowsHandler.close((Stage) inputNombre.getScene().getWindow());
    }

}
