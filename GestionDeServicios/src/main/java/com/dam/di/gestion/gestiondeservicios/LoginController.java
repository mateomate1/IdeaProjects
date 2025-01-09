package com.dam.di.gestion.gestiondeservicios;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML TextField userInput;
    @FXML PasswordField passwordField;
    private GestorBin<Usuario> gestorBin = new GestorBin<>("users.dat");
    @FXML
    public boolean access() throws IOException {
        String username = userInput.getText();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Inicio de sesion fallido, no has introducido todos los datos");
            alert.showAndWait();
            return false; // Campos vacíos, login falla
        }

        List<Usuario> usuarios =gestorBin.leer();
        for (Usuario u : usuarios){
            if (u.getUsername().equals(username)&&u.login(password)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Iniciando sesion");
                alert.showAndWait();
                WindowsHandler.load("biblioteca.fxml");
                WindowsHandler.close((Stage) userInput.getScene().getWindow());
                return true;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Inicio de sesion fallido, contraseña incorrecta");
        alert.showAndWait();
        return false;
    }
    @FXML
    public void close() throws IOException {
        WindowsHandler.load("initView.fxml");
        WindowsHandler.close((Stage) userInput.getScene().getWindow());
    }

}
